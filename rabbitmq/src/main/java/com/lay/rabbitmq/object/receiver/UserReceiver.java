package com.lay.rabbitmq.object.receiver;

import com.lay.rabbitmq.object.entity.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 19:25 2018/12/18
 * @Modified By:IntelliJ IDEA
 */
@Component
@RabbitListener(queues = "user")
public class UserReceiver {
    @RabbitHandler
    public void process(User user) {
        System.out.println("UserReceiver  : " + user.toString());
    }

}
