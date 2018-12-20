package com.lay.rabbitmqtwo.customer;

import com.lay.rabbitmqtwo.entity.User;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.xml.ws.RequestWrapper;
import java.io.IOException;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 13:57 2018/12/20
 * @Modified By:IntelliJ IDEA
 */
@Component
@RabbitListener(queues = "confirm_queue_B")
public class Customer {
    //@RabbitHandler
    public void process(Message message, Channel channel){
        System.out.println("ReceiverA："+new String(message.getBody()));
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),true);
        } catch (IOException e) {

        }
    }
    //@RabbitHandler
    public void processJsonMessage(@Payload String body, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Message message,Channel channel){
        System.out.println("ReceiverC："+body+"    deliveryTag："+deliveryTag);
        try {
            //channel.basicAck(deliveryTag,true);
            //失败确认
            channel.basicNack(deliveryTag,true,true);
            System.out.println("失败确认，重新排队");
        } catch (IOException e) {

        }
    }
    @RabbitHandler
    public void processJsonUser(@Payload User user, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Message message, Channel channel){
        System.out.println("ReceiverB："+user.toString()+"    deliveryTag："+deliveryTag);
        try {
            channel.basicAck(deliveryTag,true);
            //失败确认
            //channel.basicNack(deliveryTag,true,true);
            //System.out.println("失败确认，重新排队");
        } catch (IOException e) {

        }
    }
}
