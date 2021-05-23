package org.kzhou.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description: order-service启动类
 * @author: Admin
 * @date: 2021年05月19日 10:41
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Order2Application {

    public static void main(String[] args) {
        SpringApplication.run(Order2Application.class,args);
    }

}
