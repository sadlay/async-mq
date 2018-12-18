package com.lay.rabbitmq.queue.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 18:50 2018/12/18
 * @Modified By:IntelliJ IDEA
 */
@Component
public class HelloSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(){
        String context="hello"+new Date();
        System.out.println("Sender:"+context);
        rabbitTemplate.convertAndSend("hello",context);
    }

    public void send(int i){
        String context="Spring boot queue *****"+i;
        //System.out.println("Sender:"+context);
        rabbitTemplate.convertAndSend("hello",context);
    }
}
