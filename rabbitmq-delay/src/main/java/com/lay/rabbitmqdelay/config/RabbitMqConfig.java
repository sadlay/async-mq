package com.lay.rabbitmqdelay.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 消息队列配置信息
 * @Author: lay
 * @Date: Created in 19:40 2018/12/20
 * @Modified By:IntelliJ IDEA
 */
@Configuration
public class RabbitMqConfig {

    //消息中心实际消费队列交换配置
    @Bean
    DirectExchange messageDirectExchange() {
        return (DirectExchange) ExchangeBuilder
                .directExchange(QueueEnum.MESSAGE_QUEUE.getExchange())
                .durable(true)
                .build();
    }

    //消息中心延迟消费交换配置
    @Bean
    DirectExchange messageDirectTtlExchange() {
        return (DirectExchange) ExchangeBuilder
                .directExchange(QueueEnum.MESSAGE_TTL_QUEUE.getExchange())
                .durable(true)
                .build();
    }

    //消息中心实际消费队列配置
    @Bean
    Queue messageQueue() {
        return new Queue(QueueEnum.MESSAGE_QUEUE.getName());
    }
    //消息中心Ttl队列配置
    @Bean
    Queue messageTtlQueue() {
        return QueueBuilder
                .durable(QueueEnum.MESSAGE_TTL_QUEUE.getName())
                .withArgument("x-dead-letter-exchange", QueueEnum.MESSAGE_QUEUE.getExchange())
                .withArgument("x-dead-letter-routing-key", QueueEnum.MESSAGE_QUEUE.getRoutingKey())
                .build();
    }

    //消息中心实际消息交换机与队列绑定
    @Bean
    Binding messageBinding(DirectExchange messageDirectExchange, Queue messageQueue) {
        return BindingBuilder
                .bind(messageQueue)
                .to(messageDirectExchange)
                .with(QueueEnum.MESSAGE_QUEUE.getRoutingKey());

    }

    //消息中心TTL绑定实际TTL绑定延迟消费交换机与队列
    @Bean
    Binding messageTtlBinding(DirectExchange messageDirectTtlExchange,Queue messageTtlQueue){
        return BindingBuilder
                .bind(messageTtlQueue)
                .to(messageDirectTtlExchange)
                .with(QueueEnum.MESSAGE_TTL_QUEUE.getRoutingKey());
    }
}
