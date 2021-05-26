package org.kzhou.user.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WebConfig {


    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    /**
     * 对于配置文件：全局的rule和单独的存在冲突
     * 对于javaConfig：需要将单独的加上avoidScan避免直接就被spring扫描到
     * @return
     */
   /* @Bean
    public IRule ribbonRule() {
        // 随机 轮询 重试 最优 权重
        return new RandomRule();
        // return new RoundRobinRule();
        // return new RetryRule(new RandomRule(),1000);
        //return new AvailabilityFilteringRule();
        //return new BestAvailableRule();
        //return new WeightedResponseTimeRule();
        //return new ZoneAvoidanceRule();
        // return new NacosWeigthIRule();
    }*/
}
