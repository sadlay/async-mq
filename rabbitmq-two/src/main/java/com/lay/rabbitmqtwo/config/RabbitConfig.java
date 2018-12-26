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

    //Json格式转换
    private static final MessageConverter jsonMessageConverter=new Jackson2JsonMessageConverter();

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //测试队列A
    @Bean
    public Queue confirmQueryA() {
        return new Queue(CONFIRM_QUEUE_A);
    }

    //测试队列B
    @Bean
    public Queue confirmQueryB() {
        return new Queue(CONFIRM_QUEUE_B);
    }

    //测试交换机,类型为topic
    @Bean
    TopicExchange confirmTopicExchange() {
        return new TopicExchange(CONFIRM_EXCHANGE);
    }

    //绑定测试交换机和测试队列A
    @Bean
    Binding bindingConfirmExchangeA(Queue confirmQueryA, TopicExchange confirmTopicExchange) {
        return BindingBuilder.bind(confirmQueryA).to(confirmTopicExchange).with(CONFIRM_QUEUE_A_RoutingKey);
    }

    //绑定测试交换机和测试队列B
    @Bean
    Binding bindingConfirmExchangeB(Queue confirmQueryB, TopicExchange confirmTopicExchange) {
        return BindingBuilder.bind(confirmQueryB).to(confirmTopicExchange).with(CONFIRM_QUEUE_B_RoutingKey);
    }

    //初始化加载方法，对RabbitTemplate进行配置
    @PostConstruct
    void rabbitTemplate(){
        //消息发送确认，发送到交换器Exchange后触发回调
        rabbitTemplate.setConfirmCallback(new ConfirmCallBackHandler());
        ////消息发送确认，如果消息从交换器发送到对应队列失败时触发（比如根据发送消息时指定的routingKey找不到队列时会触发）
        rabbitTemplate.setReturnCallback(new ReturnCallBackHandler());
        //自定义格式转换
        //rabbitTemplate.setMessageConverter(jsonMessageConverter);
    }

    //RabbitMQ监听容器
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        //设置并发
        factory.setConcurrentConsumers(1);
        SimpleMessageListenerContainer s=new SimpleMessageListenerContainer();
        //最大并发
        factory.setMaxConcurrentConsumers(1);
        //消息接收——手动确认
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        //设置超时
        factory.setReceiveTimeout(2000L);
        //设置重试间隔
        factory.setFailedDeclarationRetryInterval(3000L);
        //监听自定义格式转换
        //factory.setMessageConverter(jsonMessageConverter);
        return factory;
    }



}
