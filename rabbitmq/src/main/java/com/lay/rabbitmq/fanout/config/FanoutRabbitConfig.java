package com.lay.rabbitmq.fanout.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 10:13 2018/12/20
 * @Modified By:IntelliJ IDEA
 */
@Configuration
public class FanoutRabbitConfig {
    public static final String QUEUE_A = "fanout.A";
    public static final String QUEUE_B = "fanout.B";
    public static final String QUEUE_C = "fanout.C";
    public static final String FANOUT_EXCHANGE = "fanoutExchange";

    @Bean
    public Queue AMessage() {
        return new Queue(QUEUE_A);
    }

    @Bean
    public Queue BMessage() {
        return new Queue(QUEUE_B);
    }

    @Bean
    public Queue CMessage() {
        return new Queue(QUEUE_C);
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    @Bean
    Binding bindingExchangeA(Queue AMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(AMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeB(Queue BMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(BMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeC(Queue CMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(CMessage).to(fanoutExchange);
    }
}
