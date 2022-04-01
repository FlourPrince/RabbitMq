package com.study.rabbit.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.study.rabbit.RabbitUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class WorkProvider {

    public static void main(String[] args) {
        try {
            Connection connection = RabbitUtils.getConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare("testHello", true, false, false, null);

            for (int i = 0; i < 10; i++) {
                channel.basicPublish("", "testHello", null, (i + "Hello RabbitMQ").getBytes(StandardCharsets.UTF_8));
            }
            RabbitUtils.closeConnAndChannel(connection, channel);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
