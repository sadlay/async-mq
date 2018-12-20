package com.lay.rabbitmq.msgconfirm.sender;

import com.lay.rabbitmq.msgconfirm.config.RabbitMsgConfirmConfig;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 10:42 2018/12/20
 * @Modified By:IntelliJ IDEA
 */
@Component
public class ConfirmSender {
    @Autowired
    private RabbitMessagingTemplate rabbitMessagingTemplate;


    public void send(){
        String content="hello this is a confirm message ";
        System.out.println("Confirm Sender："+content);
        rabbitMessagingTemplate.convertAndSend(RabbitMsgConfirmConfig.CONFIRM_EXCHANGE,"topic.message",content);
    }
}
