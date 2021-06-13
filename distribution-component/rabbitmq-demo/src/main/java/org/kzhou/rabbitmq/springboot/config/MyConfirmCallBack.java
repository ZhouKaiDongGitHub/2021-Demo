package org.kzhou.rabbitmq.springboot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class MyConfirmCallBack implements RabbitTemplate.ConfirmCallback {

    private Logger logger = LoggerFactory.getLogger(MyConfirmCallBack.class);

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        logger.info("发送到交换机信息确认"+"correlationData:"+correlationData+",ack:"+ack+ ",cause:"+cause);
    }
}
