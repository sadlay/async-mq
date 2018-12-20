package com.lay.rabbitmq.msgconfirm.receiver;

import com.lay.rabbitmq.msgconfirm.config.RabbitMsgConfirmConfig;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 12:01 2018/12/20
 * @Modified By:IntelliJ IDEA
 */
@Component

public class ConfirmReceiver {

    @RabbitListener(queues = RabbitMsgConfirmConfig.CONFIRM_QUEUE_A)
    public void process(Message message, Channel channel) throws IOException {
        System.out.println("ReceiverA："+new String(message.getBody()));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),true);
    }
}
