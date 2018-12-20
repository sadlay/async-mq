package com.lay.rabbitmq;

import com.lay.rabbitmq.msgconfirm.sender.ConfirmSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 10:47 2018/12/20
 * @Modified By:IntelliJ IDEA
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqConfirmTest {
    @Autowired
    ConfirmSender confirmSender;

    @Test
    public void test() {
        confirmSender.send();
    }
}
