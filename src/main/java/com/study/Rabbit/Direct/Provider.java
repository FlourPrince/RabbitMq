package com.study.Rabbit.Direct;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.study.Rabbit.RabbitUtils;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


public class Provider {

   @Test
    public void testMessage(){

       try {
           Connection connection= RabbitUtils.getConnection();
           Channel channel= connection.createChannel();
           //通道绑定消息队列
           //1.队列名称
           //2.队列是否持久化
           //3.是否独占队列
           //4.是否在消费后删除队列
           //5.额外参数
           channel.queueDeclare("testHello",false,false,false,null);

           //发布消息
           //1.交换机名称
           //2.队列名称
           //3.传递消息额外设置
           //4.消息内容
           channel.basicPublish("","testHello",null,"Hello RabbitMQ".getBytes(StandardCharsets.UTF_8));
           System.out.printf("发送成功");
           channel.close();
           connection.close();
       } catch (IOException e) {
           e.printStackTrace();
       }


   }
}
