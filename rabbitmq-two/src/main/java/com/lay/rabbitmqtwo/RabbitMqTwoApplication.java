package com.lay.rabbitmqtwo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 13:30 2018/12/20
 * @Modified By:IntelliJ IDEA
 */
@SpringBootApplication
@EnableAsync
public class RabbitMqTwoApplication {
    public static void main(String[] args) {
        SpringApplication.run(RabbitMqTwoApplication.class, args);
    }

}
