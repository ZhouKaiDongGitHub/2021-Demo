package org.kzhou.rabbitmq.spring;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ProducerClient {

    public static void sendByExchange(String message) throws IOException, TimeoutException {
        Connection connection = MQConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare("testExchange","fanout",true); //direct topic fanout
        channel.queueDeclare("testQueue",true,false,false,null);
        channel.queueBind("testQueue","testExchange","");
        channel.basicPublish("testExchange","",null,message.getBytes());
        System.out.println("发送的消息为："+message);

        channel.close();
        connection.close();
    }

    public static void main(String[] args) {
        try {
            sendByExchange("测试数据");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
