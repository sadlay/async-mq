package com.lay.rabbitmq.queue.config;



import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Description:
 * @Author: lay
 * @Date: Created in 18:36 2018/12/18
 * @Modified By:IntelliJ IDEA
 */
//@Configuration
//@EnableRabbit
public class RabbitConfig {
    @Bean
    public Queue queue(){
        return new Queue("hello");
    }

}
