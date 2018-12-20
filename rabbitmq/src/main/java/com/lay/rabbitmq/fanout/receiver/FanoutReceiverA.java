package com.lay.rabbitmq.fanout.receiver;

import com.lay.rabbitmq.fanout.config.FanoutRabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 13:44 2018/12/19
 * @Modified By:IntelliJ IDEA
 */
@Component
public class FanoutReceiverA {


    @RabbitListener(queues = FanoutRabbitConfig.QUEUE_A)
    public void process(String context){
        System.out.println("ReceiverA: "+context);
    }

    @RabbitListener(queues = FanoutRabbitConfig.QUEUE_A)
    public void process2(String context){
        System.out.println("ReceiverD: "+context);
    }
}
