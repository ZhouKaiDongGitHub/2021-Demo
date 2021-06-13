package org.kzhou.rabbitmq.springboot.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;


public class MyReturnCallBack implements RabbitTemplate.ReturnCallback {

    private Logger logger = LoggerFactory.getLogger(MyReturnCallBack.class);

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        logger.info("失败回调信息："+message+",replyCode："+replyText+",replyText："+replyText+"，交换机为："+exchange+"，路由键："+routingKey);
    }
}
