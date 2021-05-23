package org.kzhou.order.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.Assert;
import org.springframework.web.client.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Random;

/**
 * 自定义restTemplate，模仿Ribbon的功能
 */
public class MyRestTemplate extends RestTemplate {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Override
    protected <T> T doExecute(URI url, HttpMethod method, RequestCallback requestCallback, ResponseExtractor<T> responseExtractor) throws RestClientException {

        Assert.notNull(url, "URI is required");
        Assert.notNull(method, "HttpMethod is required");
        ClientHttpResponse response = null;
        try {
            url = replaceUrl(url);
            ClientHttpRequest request = createRequest(url, method);
            if (requestCallback != null) {
                requestCallback.doWithRequest(request);
            }
            response = request.execute();
            handleResponse(url, method, response);
            return (responseExtractor != null ? responseExtractor.extractData(response) : null);
        }
        catch (IOException ex) {
            String resource = url.toString();
            String query = url.getRawQuery();
            resource = (query != null ? resource.substring(0, resource.indexOf('?')) : resource);
            throw new ResourceAccessException("I/O error on " + method.name() +
                    " request for \"" + resource + "\": " + ex.getMessage(), ex);
        }
        finally {
            if (response != null) {
                response.close();
            }
        }
    }

    /**
     * 服务之间的调用，一般都在内网中，请求的格式大约如下：
     * http://192.168.1.1：8080/api/payBilling?id=1 ，
     * http://billing-service/api/payBilling?id=1
     */
    private URI replaceUrl(URI originalUrl){
       String originalUrlString =  originalUrl.toString();
       String httpUrl =  originalUrlString.split("//")[1];
       int index = httpUrl.replaceFirst("/","@").indexOf("@");
       String serviceName = httpUrl.substring(0,index);
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceName);
        if(serviceInstances.isEmpty()){
            throw new RuntimeException("没有可用的实例列表"+serviceName);
        }
        // 采用随机的方式
        Random random = new Random();
        Integer randomIndex = random.nextInt(serviceInstances.size());
        String serviceIp = serviceInstances.get(randomIndex).getUri().toString();
        System.out.println("随机选择的服务器ip："+ serviceIp);
        String targetSource = httpUrl.replace(serviceName,serviceIp);
        try {
            return new URI(targetSource);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return originalUrl;
    }
}
