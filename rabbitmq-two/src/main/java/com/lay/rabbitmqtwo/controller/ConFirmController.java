package com.lay.rabbitmqtwo.controller;

import com.lay.rabbitmqtwo.producer.Producer;
import com.rabbitmq.client.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 11:23 2018/12/20
 * @Modified By:IntelliJ IDEA
 */
@RestController
public class ConFirmController {
    @Autowired
    private Producer producer;
    @Autowired
    RabbitTemplate rabbitTemplate;


    @PostConstruct
    public void init(){
        System.out.println("");
    }

    @GetMapping("/rabbitmq/confirm")
    public String confirm(){
        for(int i=0;i<5000;i++) {
            try {
                producer.send();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "成功";
    }

    @GetMapping("/rabbitmq/confirmUser")
    public String confirmUser(){
        for(int i=0;i<5000;i++) {
            producer.sendUser(i);
        }
        return "成功";
    }

    @GetMapping("/rabbitmq/listen")
    public String confirmListen(){
        //定义queue名称
        String QUEUE_NAME = "test-callbak-queue";

        //ConnectionFactory就是抽象了底层的Socket网络连接
        ConnectionFactory factory=new ConnectionFactory();
        //设置参数
        factory.setHost("192.168.3.253");
        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("123456");
        String message=null;
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            //定义rabbitmq的aueue队列，如果不存在就创建一个
            channel.queueDeclare(QUEUE_NAME,false,false,false,null);
            //定义消息
            message = "订单消息";
            channel.confirmSelect();
            channel.addConfirmListener(new ConfirmListener() {
                @Override
                public void handleAck(long deliveryTag, boolean multipe) throws IOException {
                    System.out.println(MessageFormat.format("deliveryTag:{0},multipe:{1}",deliveryTag,multipe));
                }

                @Override
                public void handleNack(long deliveryTag, boolean multipe) throws IOException {
                    System.out.println(MessageFormat.format("deliveryTag:{0},multipe:{1}",deliveryTag,multipe));
                }
            });
            //发送到指定队列
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
            System.out.println("发送队列: "+message);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return message;
    }
    @GetMapping("/rabbitmq/listen2")
    public String confirmListen2(){
        //定义queue名称
        String QUEUE_NAME = "test-callbak-queue";

        //ConnectionFactory就是抽象了底层的Socket网络连接
        ConnectionFactory factory=new ConnectionFactory();
        //设置参数
        factory.setHost("192.168.3.253");
        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("123456");
        String message=null;
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            //定义rabbitmq的aueue队列，如果不存在就创建一个
            channel.queueDeclare(QUEUE_NAME,false,false,false,null);

            DeliverCallback deliverCallback=(consumerTag,delivery)->{
                String messaage=new String(delivery.getBody());
                System.out.println(" 接收消息  ："+messaage);
            };
            channel.basicConsume(QUEUE_NAME,deliverCallback, consumer->{});
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return message;
    }
}
