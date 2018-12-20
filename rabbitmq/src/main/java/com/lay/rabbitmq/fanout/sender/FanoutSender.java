package com.lay.rabbitmq.fanout.sender;

import com.lay.rabbitmq.fanout.config.FanoutRabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 13:42 2018/12/19
 * @Modified By:IntelliJ IDEA
 */
@Component
public class FanoutSender {

    // @Autowired
    //private AmqpTemplate rabbitTemplate;
    @Autowired
    @Lazy
    RabbitTemplate rabbitTemplate;

    public void send(){
        String context="this is a fanout message";
        System.out.println("Sender"+context);
        rabbitTemplate.convertAndSend(FanoutRabbitConfig.FANOUT_EXCHANGE,"",context);
    }
}
