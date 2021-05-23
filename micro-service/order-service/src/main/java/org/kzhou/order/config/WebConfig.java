package org.kzhou.order.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.kzhou.order.util.MyRestTemplate;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Configuration
public class WebConfig {

    @Bean(name = "restTemplate1")
    public RestTemplate restTemplate1(){
        return new RestTemplate();
    }

    @Bean(name = "restTemplate2")
    public RestTemplate restTemplate2(){
        return new MyRestTemplate();
    }

    @LoadBalanced
    @Bean(name = "restTemplate3")
    public RestTemplate restTemplate3(){
        return new RestTemplate();
    }

}
