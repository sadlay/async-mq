package com.lay.rabbitmq.topic.receiver;

import com.lay.rabbitmq.topic.config.TopicRabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 19:49 2018/12/18
 * @Modified By:IntelliJ IDEA
 */
@Component
@RabbitListener(queues = TopicRabbitConfig.messages)
public class TopicReceiver2 {
    @RabbitHandler
    public void process(String context){
        System.out.println("TopicReceiver 2: "+context);
    }
}
