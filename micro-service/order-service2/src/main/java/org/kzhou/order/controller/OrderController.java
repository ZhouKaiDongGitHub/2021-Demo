package org.kzhou.order.controller;

import org.kzhou.base.response.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    @GetMapping(value = "/createOrder")
    public ResponseModel createOrder() {
        logger.info("订单模块：下单成功2");
        return new ResponseModel().setCode("200").setMessage("下单成功2");
    }
}
