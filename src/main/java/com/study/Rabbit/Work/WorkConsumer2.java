package com.study.Rabbit.Work;

import com.rabbitmq.client.*;
import com.study.Rabbit.RabbitUtils;

import java.io.IOException;

public class WorkConsumer2 {
    public static void main(String[] args) {

        try {
            Connection connection = RabbitUtils.getConnection();
            Channel channel = connection.createChannel();

            channel.queueDeclare("testHello", false, false, false, null);
            channel.basicConsume("testHello", true, new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    System.out.println(new String(body));
                    System.out.println("消费结束");
                }
            });


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
