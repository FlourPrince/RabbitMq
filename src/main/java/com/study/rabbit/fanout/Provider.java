package com.study.rabbit.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.study.rabbit.RabbitUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Provider {
    public static void main(String[] args) {


        try {
            Connection connection= RabbitUtils.getConnection();
            Channel channel= connection.createChannel();
            //声明交换机
            // 1.交换机名称
            // 2.交换机类型
            channel.exchangeDeclare("logs","fanout");

            //发布消息
            //1.交换机名称
            //2.队列名称
            //3.传递消息额外设置
            //4.消息内容
            channel.basicPublish("logs","",null,"Hello RabbitMQ".getBytes(StandardCharsets.UTF_8));
            System.out.printf("发送成功");
            RabbitUtils.closeConnAndChannel(connection,channel);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
