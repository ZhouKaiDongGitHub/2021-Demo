package org.kzhou.rabbitmq.springboot.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("org.kzhou.rabbitmq.springboot")
public class MqConfig {

    @Bean
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("127.0.0.1",5672);
        connectionFactory.setUsername("kzhou");
        connectionFactory.setPassword("11111");
        connectionFactory.setVirtualHost("testHost");

        connectionFactory.setPublisherConfirms(true); //发送确认

        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);

        rabbitTemplate.setConfirmCallback(new MyConfirmCallBack()); //发送确认

        rabbitTemplate.setMandatory(true);//设置手动确认
        rabbitTemplate.setReturnCallback(new MyReturnCallBack());
        return rabbitTemplate;
    }


    @Bean
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(ConnectionFactory connectionFactory){
        SimpleRabbitListenerContainerFactory containerFactory = new SimpleRabbitListenerContainerFactory();
        containerFactory.setConnectionFactory(connectionFactory);
        containerFactory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        containerFactory.setPrefetchCount(1);
        return containerFactory;
    }

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("directExchange");
    }

    @Bean
    public Queue queue1(){
        return new Queue("directQueue1",true);
    }
    @Bean
    public Queue queue2(){
        return new Queue("directQueue2",true);
    }

    @Bean
    public Binding binding1(){
        return BindingBuilder.bind(queue1()).to(directExchange()).with("direct.key1");
    }

    @Bean
    public Binding binding2(){
        return BindingBuilder.bind(queue2()).to(directExchange()).with("direct.key2");
    }
}
