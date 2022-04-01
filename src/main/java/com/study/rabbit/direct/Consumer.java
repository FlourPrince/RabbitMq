package com.study.rabbit.direct;


import com.rabbitmq.client.*;
import com.study.rabbit.RabbitUtils;

import java.io.IOException;

/**
 * 默认情况下平局分配
 */
public class Consumer {

    public static void main(String[] args) {

        try {
            Connection connection= RabbitUtils.getConnection();
            Channel channel = connection.createChannel();
            //通道绑定消息队列
            //1.队列名称
            //2.队列是否持久化
            //3.是否独占队列
            //4.是否在消费后删除队列
            //5.额外参数
            channel.queueDeclare("testHello", false, false, false, null);

            //消费消息
            //1.队列名称
            //2.消息自动确认机制
            //3.消费回调接口
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
