package com.study.rabbit.direct;

import com.rabbitmq.client.*;
import com.study.rabbit.RabbitUtils;

import java.io.IOException;

public class Consumer1 {
    public static void main(String[] args) {


        try {
            Connection connection= RabbitUtils.getConnection();
            Channel channel = connection.createChannel();

            channel.exchangeDeclare("logs-direct","direct");

            String queue=channel.queueDeclare().getQueue();

            channel.queueBind(queue,"logs-direct","info");

            channel.basicConsume(queue, true, new DefaultConsumer(channel) {
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
