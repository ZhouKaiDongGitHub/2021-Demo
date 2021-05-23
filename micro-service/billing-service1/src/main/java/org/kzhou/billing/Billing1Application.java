package org.kzhou.billing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description: BillingApplication 启动类
 * @author: Admin
 * @date: 2021年05月21日 11:07
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Billing1Application {


    public static void main(String[] args) {
        SpringApplication.run(Billing1Application.class,args);
    }
}
