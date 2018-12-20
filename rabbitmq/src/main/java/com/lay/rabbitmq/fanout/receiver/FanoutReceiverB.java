package com.lay.rabbitmq.fanout.receiver;

import com.lay.rabbitmq.fanout.config.FanoutRabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 13:49 2018/12/19
 * @Modified By:IntelliJ IDEA
 */
@Component
@RabbitListener(queues = FanoutRabbitConfig.QUEUE_B)
public class FanoutReceiverB {

    @RabbitHandler
    public void process(String context){
        //System.out.println("ReceiverB: "+context);
    }
}
