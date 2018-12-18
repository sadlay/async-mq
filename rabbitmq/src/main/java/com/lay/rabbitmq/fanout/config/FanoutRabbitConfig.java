package com.lay.rabbitmq.fanout.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 20:13 2018/12/18
 * @Modified By:IntelliJ IDEA
 */
@Configuration
public class FanoutRabbitConfig {
    @Bean
    public Queue Amessage(){
        return null;
    }
}
