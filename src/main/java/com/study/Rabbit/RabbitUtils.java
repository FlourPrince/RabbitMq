package com.study.Rabbit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

public class RabbitUtils {
    private static ConnectionFactory connectionFactory = null;

    static {
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("47.100.26.201");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/ems");
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("123456");
    }

    public static Connection getConnection() {
        try {
            return connectionFactory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeConnAndChannel(Connection connection, Channel channel) {
        try {
            if (channel != null) {
                channel.close();
            }
            if (connection != null) {
                connection.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

