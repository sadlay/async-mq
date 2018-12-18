package com.lay.rabbitmq.object.config;



import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Description:
 * @Author: lay
 * @Date: Created in 18:36 2018/12/18
 * @Modified By:IntelliJ IDEA
 */
@Configuration
public class RabbitObjectConfig {

    @Bean
    public Queue userQueue(){
        return new Queue("user");
    }
}
