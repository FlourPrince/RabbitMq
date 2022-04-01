package com.study.rabbit.Topic;

import com.rabbitmq.client.*;
import com.study.rabbit.RabbitUtils;

import java.io.IOException;

public class Consumer {

    public static void main(String[] args) {
    Connection connection= RabbitUtils.getConnection();

        Channel channel = null;
        try {
            channel = connection.createChannel();


            channel.exchangeDeclare("logs-topic", "topic");


            String routeKey1 = channel.queueDeclare().getQueue();

            //动态通配符
            channel.queueBind(routeKey1,"logs-topic","user.*");

            channel.basicConsume(routeKey1, true, new DefaultConsumer(channel) {
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
