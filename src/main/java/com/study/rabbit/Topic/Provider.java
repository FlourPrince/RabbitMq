package com.study.rabbit.Topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.study.rabbit.RabbitUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Provider {

    public static void main(String[] args) {


        try {
            Connection connection = RabbitUtils.getConnection();

            Channel channel = connection.createChannel();

            channel.exchangeDeclare("logs-topic", "topic");

            String routeKey1 = "user.topic";

            channel.basicPublish("logs-topic", routeKey1, null, (routeKey1 + "Hello RabbitMQ").getBytes(StandardCharsets.UTF_8));

            System.out.println("发送成功");

            RabbitUtils.closeConnAndChannel(connection,channel);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
