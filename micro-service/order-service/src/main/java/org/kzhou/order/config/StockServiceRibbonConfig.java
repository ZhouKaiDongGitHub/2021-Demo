package org.kzhou.order.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import com.netflix.loadbalancer.ZoneAvoidanceRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StockServiceRibbonConfig {

    @Bean(name = "stockServiceRule")
    public IRule rule() {
        //随机 轮询 权重 最优
        return new RoundRobinRule();
    }
}
