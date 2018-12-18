package com.lay.rabbitmq.topic.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 19:46 2018/12/18
 * @Modified By:IntelliJ IDEA
 */
@Component
public class TopicSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send1(){
        String conText="this is message 1";
        System.out.println("Sender: "+conText);
        rabbitTemplate.convertAndSend("topicExchange","topic.message",conText);
    }

    public void send2(){
        String conText="this is messages 2";
        System.out.println("Sender: "+conText);
        rabbitTemplate.convertAndSend("topicExchange","topic.messages",conText);
    }
}
