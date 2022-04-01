package com.study.rabbit.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.study.rabbit.RabbitUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Provider1 {
    public static void main(String[] args) {

        try {
            Connection connection = RabbitUtils.getConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare("logs-direct", "direct");
            String routeKey1 = "info";
            String routeKey2= "error";

            channel.basicPublish("logs-direct", routeKey1, null,(routeKey1+"Hello RabbitMQ").getBytes(StandardCharsets.UTF_8));

            channel.basicPublish("logs-direct", routeKey2, null, (routeKey2+"Hello RabbitMQ").getBytes(StandardCharsets.UTF_8));



            System.out.printf("发送成功");
            RabbitUtils.closeConnAndChannel(connection, channel);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
