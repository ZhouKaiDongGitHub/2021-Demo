package org.kzhou.rabbitmq.spring;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConsumerClient {

    public static void getMessage() throws IOException, TimeoutException {
        Connection connection = MQConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        DefaultConsumer defaultConsumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body) throws IOException{
                System.out.println(new String(body,"UTF-8"));
            }
        };

        channel.basicConsume("directQueue",defaultConsumer);
    }

    public static void main(String[] args) {
        try {
            getMessage();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
