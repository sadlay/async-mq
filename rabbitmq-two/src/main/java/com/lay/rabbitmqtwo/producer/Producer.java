package com.lay.rabbitmqtwo.producer;

import com.lay.rabbitmqtwo.entity.User;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 13:36 2018/12/20
 * @Modified By:IntelliJ IDEA
 */
@Component
public class Producer {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Async
    public void send() throws IOException {
        System.out.println(Thread.currentThread().getId());
        Channel channel = rabbitTemplate.getConnectionFactory().createConnection().createChannel(true);
        channel.confirmSelect();
        rabbitTemplate.convertAndSend("confirm_topic_exchange","topic.messagebbb","ceshi");
    }

    @Async
    public void sendUser(int i){
        System.out.println("线程名称："+Thread.currentThread().getName()+"线程ID :"+Thread.currentThread().getId());
        User user=new User();
        user.setId(Long.valueOf(i));
        user.setUserName("用户"+i);
        user.setSex((i%2==0)?"男":"女");
        user.setAge((int) (Math.random()*100));
        System.out.println(user.toString());
        rabbitTemplate.convertAndSend("confirm_topic_exchange","topic.messagebbb",user);
    }
}
