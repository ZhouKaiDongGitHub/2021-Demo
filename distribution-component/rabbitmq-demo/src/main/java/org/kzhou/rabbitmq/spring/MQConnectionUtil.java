package org.kzhou.rabbitmq.spring;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MQConnectionUtil {

    public static Connection getConnection() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("kzhou");
        connectionFactory.setPassword("11111");
        connectionFactory.setVirtualHost("testHost");
        return connectionFactory.newConnection();
    }
}
