package com.lay.rabbitmqtwo;

import com.lay.rabbitmqtwo.producer.Producer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 13:38 2018/12/20
 * @Modified By:IntelliJ IDEA
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqTwoTest {
    @Autowired
    Producer producer;
    @Test
    public void test(){
        producer.send();
    }
}
