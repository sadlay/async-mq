package com.lay.rabbitmq;

import com.lay.rabbitmq.topic.sender.TopicSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 19:56 2018/12/18
 * @Modified By:IntelliJ IDEA
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqTopicTest {
    @Autowired
    private TopicSender topicSender;

    @Test
    public void topicTest(){
        topicSender.send1();
        //topicSender.send2();
    }
}
