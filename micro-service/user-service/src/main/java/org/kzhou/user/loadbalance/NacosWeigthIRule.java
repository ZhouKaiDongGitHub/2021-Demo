package org.kzhou.user.loadbalance;


import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class NacosWeigthIRule extends AbstractLoadBalancerRule {

    private Logger logger = LoggerFactory.getLogger(NacosWeigthIRule.class);

    @Autowired
    private NacosDiscoveryProperties nacosDiscoveryProperties;

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {
        // ribbon内部使用，读取配置文件信息
    }

    @Override
    public Server choose(Object key) {
        BaseLoadBalancer baseLoadBalancer =(BaseLoadBalancer) this.getLoadBalancer();
        String serviceName = baseLoadBalancer.getName();

        NamingService namingService = nacosDiscoveryProperties.namingServiceInstance();
        try {
            Instance instance = namingService.selectOneHealthyInstance(serviceName);
            return new NacosServer(instance);
        } catch (NacosException e) {
            e.printStackTrace();
        }
       return null;
    }
}
