package org.kzhou.billing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: 监控接口
 * @author: Admin
 * @date: 2021年05月19日 11:02
 */
@RestController
@RequestMapping("/billing")
public class MonitorController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping(value = "/monitor")
    public String echo() {
        return "ok";
    }

    /**
     * [
     *     {
     *         "serviceId": "billing-service",
     *             "host": "192.168.78.1",
     *             "port": 8081,
     *             "secure": false,
     *             "metadata": {
     *                  "nacos.instanceId": "192.168.78.1#8081#DEFAULT#sz@@billing-service",
     *                 "nacos.weight": "1.0",
     *                 "nacos.cluster": "DEFAULT",
     *                 "nacos.ephemeral": "true",
     *                 "nacos.healthy": "true",
     *                 "preserved.register.source": "SPRING_CLOUD"
     *                   },
     *              "uri": "http://192.168.78.1:8081",
     *             "scheme": null,
     *             "instanceId": null
     *     },
     *     {
     *         "serviceId": "billing-service",
     *             "host": "192.168.78.1",
     *             "port": 8082,
     *             "secure": false,
     *             "metadata": {
     *                  "nacos.instanceId": "192.168.78.1#8082#DEFAULT#sz@@billing-service",
     *                 "nacos.weight": "1.0",
     *                 "nacos.cluster": "DEFAULT",
     *                 "nacos.ephemeral": "true",
     *                 "nacos.healthy": "true",
     *                 "preserved.register.source": "SPRING_CLOUD"
     *             },
     *              "uri": "http://192.168.78.1:8082",
     *             "scheme": null,
     *             "instanceId": null
     *     }
     * ]
     */
    @GetMapping(value = "/service")
    public List<ServiceInstance> getService() {
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("billing-service");
        return serviceInstances;
    }
}
