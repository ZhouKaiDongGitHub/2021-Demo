package org.kzhou.order.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.ZoneAvoidanceRule;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RibbonClients(value = {@RibbonClient(name = "billing-service", configuration = BillingServiceRibbonConfig.class)
        ,@RibbonClient(name = "stock-service",configuration = StockServiceRibbonConfig.class)})
public class RibbonConfig {

    @Bean
    public IRule rule() {
        //随机 轮询 权重 最优
        return new ZoneAvoidanceRule();
    }

}
