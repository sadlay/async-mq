package com.lay.rabbitmqtwo.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 13:34 2018/12/20
 * @Modified By:IntelliJ IDEA
 */
@Configuration
public class RabbitConfig {
    public static final String CONFIRM_QUEUE_A = "confirm_queue_A";
    public static final String CONFIRM_QUEUE_B = "confirm_queue_B";
    public static final String CONFIRM_EXCHANGE = "confirm_topic_exchange";
    private static final String CONFIRM_QUEUE_A_RoutingKey="topic.message";
    private static final String CONFIRM_QUEUE_B_RoutingKey="topic.#";
    private static final MessageConverter jsonMessageConverter=new Jackson2JsonMessageConverter();

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Bean
    public Queue confirmQueryA() {
        return new Queue(CONFIRM_QUEUE_A);
    }

    @Bean
    public Queue confirmQueryB() {
        return new Queue(CONFIRM_QUEUE_B);
    }


    @Bean
    TopicExchange confirmTopicExchange() {
        return new TopicExchange(CONFIRM_EXCHANGE);
    }

    @Bean
    Binding bindingConfirmExchangeA(Queue confirmQueryA, TopicExchange confirmTopicExchange) {
        return BindingBuilder.bind(confirmQueryA).to(confirmTopicExchange).with(CONFIRM_QUEUE_A_RoutingKey);
    }

    @Bean
    Binding bindingConfirmExchangeB(Queue confirmQueryB, TopicExchange confirmTopicExchange) {
        return BindingBuilder.bind(confirmQueryB).to(confirmTopicExchange).with(CONFIRM_QUEUE_B_RoutingKey);
    }



    @PostConstruct
    void rabbitTemplate(){
        rabbitTemplate.setConfirmCallback(new ConfirmCallBackHandler());
        rabbitTemplate.setReturnCallback(new ReturnCallBackHandler());
        //rabbitTemplate.setMessageConverter(jsonMessageConverter);
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setConcurrentConsumers(1);
        SimpleMessageListenerContainer s=new SimpleMessageListenerContainer();
        factory.setMaxConcurrentConsumers(1);
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        factory.setReceiveTimeout(2000L);
        factory.setFailedDeclarationRetryInterval(3000L);
        //factory.setMessageConverter(jsonMessageConverter);
        return factory;
    }



}
