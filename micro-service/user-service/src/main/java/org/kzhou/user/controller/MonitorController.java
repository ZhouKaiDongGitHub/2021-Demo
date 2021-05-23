package org.kzhou.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: 监控接口
 * @author: Admin
 * @date: 2021年05月19日 11:02
 */
@RestController
@RequestMapping("/user")
public class MonitorController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping(value = "/monitor")
    public String echo() {
        return "ok";
    }

    @GetMapping(value = "/service")
    public List<ServiceInstance> getService() {
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("user-service");
        return serviceInstances;
    }

}
