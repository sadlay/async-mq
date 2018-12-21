package com.lay.rabbitmqdelay.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 利用x-delayed-message-exchange插件，解决rabbitmq死信队列延时大会阻塞延时小的消息
 * @Author: lay
 * @Date: Created in 13:16 2018/12/21
 * @Modified By:IntelliJ IDEA
 */
@Configuration
public class XdelayConfig {
    //队列名
    public static final String X_DELAY_MESSAGE_QUEUE="x.delay.message";
    public static final String X_DELAY_MESSAGE_EXCHANGE="x.delay.exchange";
    public static final String X_DELAY_ROUTING_KEY="x.delay.routing.key";

    //创建一个真实消费队列
    @Bean
    Queue xDelayMessageQueue(){
        return new Queue(X_DELAY_MESSAGE_QUEUE,true);
    }

    @Bean
    CustomExchange xDelayExchange(){
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange(X_DELAY_MESSAGE_EXCHANGE, "x-delayed-message", true, false, args);
    }

    @Bean
    Binding xDelayMessageBinding(Queue xDelayMessageQueue,CustomExchange xDelayExchange){
        return BindingBuilder
                .bind(xDelayMessageQueue)
                .to(xDelayExchange)
                .with(X_DELAY_ROUTING_KEY)
                .noargs();
    }
}
