package org.kzhou.order.controller;

import org.kzhou.base.response.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    RestTemplate restTemplate1;

    @Autowired
    RestTemplate restTemplate2;

    @Autowired
    RestTemplate restTemplate3;

    @GetMapping(value = "/createOrder1")
    public String createOrder1() {

       String response =  restTemplate1.getForObject("http://localhost:8081/billing/createBilling",String.class);

       return "创建订单成功"+ response;
    }

    @GetMapping(value = "/createOrder2")
    public String createOrder2() {

        String response =  restTemplate2.getForObject("http://billing-service/billing/createBilling",String.class);

        return "创建订单成功"+ response;
    }

    @GetMapping(value = "/createOrder3")
    public String createOrder3() {

        String response =  restTemplate3.getForObject("http://billing-service/billing/createBilling",String.class);

        return "创建订单成功"+ response;
    }

    @GetMapping(value = "/createOrder")
    public ResponseModel createOrder() {
        logger.info("订单模块：下单成功1");
       return new ResponseModel().setCode("200").setMessage("下单成功1");
    }
}
