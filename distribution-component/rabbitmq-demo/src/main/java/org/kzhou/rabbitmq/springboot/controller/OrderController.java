package org.kzhou.rabbitmq.springboot.controller;

import org.kzhou.rabbitmq.springboot.client.RmqProducerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    RmqProducerClient rmqProducerClient;

    @RequestMapping("/message1.do")
    public Object message1(String message){
        rmqProducerClient.sendMessage("directExchange","direct.key1",message);
        return "发送消息成功";
    }

    @RequestMapping("/message2.do")
    public Object message2(String message){
        rmqProducerClient.sendMessage("directExchange","direct.key2",message);
        return "发送消息成功";
    }
}

