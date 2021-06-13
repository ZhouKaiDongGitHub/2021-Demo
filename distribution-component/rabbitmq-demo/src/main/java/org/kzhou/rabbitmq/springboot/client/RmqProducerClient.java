package org.kzhou.rabbitmq.springboot.client;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RmqProducerClient {

    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     *
     * @param message 消息
     * @param routingKey 路由键
     * @param exchangeName 交换机
     */
    public void sendMessage(String exchangeName,String routingKey,String message){
        CorrelationData correlationData = new CorrelationData("订单ID:1");
        rabbitTemplate.convertAndSend(exchangeName,routingKey,message,correlationData);
    }
}
