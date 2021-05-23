package org.kzhou.stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Stock1Application {

    public static void main(String[] args) {
        SpringApplication.run(Stock1Application.class,args);
    }
}
