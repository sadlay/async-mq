package com.lay.rabbitmq;

import com.lay.rabbitmq.object.entity.User;
import com.lay.rabbitmq.object.sender.UserSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 19:30 2018/12/18
 * @Modified By:IntelliJ IDEA
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqUserTest {
    @Autowired
    private UserSender userSender;

    @Test
    public void userTest(){
        User user=new User();
        user.setId(1L);
        user.setUserName("xiaohua");
        user.setSex("女");
        user.setAge(18);

        userSender.send(user);
    }
}
