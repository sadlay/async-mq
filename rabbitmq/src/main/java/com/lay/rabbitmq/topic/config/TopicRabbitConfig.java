package com.lay.rabbitmq.topic.config;



import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Description:
 * @Author: lay
 * @Date: Created in 18:36 2018/12/18
 * @Modified By:IntelliJ IDEA
 */
@Configuration
public class TopicRabbitConfig {
    public final static String message = "topic.message";
    public final static String messages = "topic.messages";
    @Bean
    public Queue queueMessage(){
        return new Queue(TopicRabbitConfig.message);
    }

    @Bean
    public Queue queueMessages(){
        return new Queue(TopicRabbitConfig.messages);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange("topicExchange");
    }
    @Bean
    public Binding bindExchangeMessage(Queue queueMessage,TopicExchange exchange){
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
    }
    @Bean
    public Binding bindExchangeMessages(Queue queueMessages,TopicExchange exchange){
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
    }

}
