package org.kzhou.order.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.ZoneAvoidanceRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BillingServiceRibbonConfig {

    @Bean(name = "billingServiceRule")
    public IRule rule() {
        //随机 轮询 权重 最优
        return new RandomRule();
    }
}
