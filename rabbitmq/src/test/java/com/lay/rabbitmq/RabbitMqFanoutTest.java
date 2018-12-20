package com.lay.rabbitmq;

import com.lay.rabbitmq.fanout.sender.FanoutSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 13:51 2018/12/19
 * @Modified By:IntelliJ IDEA
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqFanoutTest {

    @Autowired
    FanoutSender fanoutSender;

    @Test
    public void fanoutTest(){
        for (int i=0;i<10;i++) {
            fanoutSender.send();
        }
    }
}
