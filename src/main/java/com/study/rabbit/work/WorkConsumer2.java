package com.study.rabbit.work;

import com.rabbitmq.client.*;
import com.study.rabbit.RabbitUtils;

import java.io.IOException;

public class WorkConsumer2 {
    public static void main(String[] args) {

        try {
            Connection connection = RabbitUtils.getConnection();
            final Channel channel = connection.createChannel();
            //1.每次消费一条消息
            channel.basicQos(1);
            channel.queueDeclare("testHello", true, false, false, null);
            channel.basicConsume("testHello", false, new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    System.out.println(new String(body));
                    //2.手动消息确认
                    channel.basicAck(envelope.getDeliveryTag(), false);
                    System.out.println("消费结束");
                }
            });


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
