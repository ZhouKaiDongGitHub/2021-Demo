package com.kzhou.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Description: Nacos Service 启动类
 * @author: Admin
 * @date: 2021年05月19日 11:14
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Nacos1Application {

    public static void main(String[] args) {
        SpringApplication.run(Nacos1Application.class,args);
    }

    @RestController
    public class EchoController {
        @GetMapping(value = "/echo/{string}")
        public String echo(@PathVariable String string) {
            return "Hello Nacos Discovery " + string;
        }
    }
}
