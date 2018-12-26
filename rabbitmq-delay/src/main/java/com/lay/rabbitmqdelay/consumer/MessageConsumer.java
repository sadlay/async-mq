package com.lay.rabbitmqdelay.consumer;

import com.alibaba.fastjson.JSON;
import com.lay.rabbitmqdelay.config.QueueEnum;
import com.lay.rabbitmqdelay.config.XdelayConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 10:32 2018/12/21
 * @Modified By:IntelliJ IDEA
 */
@Component
@RabbitListener(queues = "message.create")
public class MessageConsumer {
    private static final Logger log= LoggerFactory.getLogger(MessageConsumer.class);
    @RabbitHandler
    public void process(@Payload String content, Message message){
        log.info("消费内容：{}",content);
        log.info("消费时间：{}",new Date());
        log.info("Message内容: {}", JSON.toJSONString(message));
    }
}
