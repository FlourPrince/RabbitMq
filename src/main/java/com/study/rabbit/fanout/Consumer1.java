package com.study.rabbit.fanout;

import com.rabbitmq.client.*;
import com.study.rabbit.RabbitUtils;

import java.io.IOException;

public class Consumer1 {
    public static void main(String[] args) {
        try {
            Connection connection = RabbitUtils.getConnection();
            Channel channel = connection.createChannel();
            //声明交换机
            // 1.交换机名称
            // 2.交换机类型
            channel.exchangeDeclare("logs", "fanout");
            //获取交换机队列
            String queue = channel.queueDeclare().getQueue();
            //绑定交换机队列
            //1.队列名称
            //2.交换机名称
            channel.queueBind(queue, "logs", "");
            //消费消息
            //1.队列名称
            //2.消息自动确认机制
            //3.消费回调接口
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
