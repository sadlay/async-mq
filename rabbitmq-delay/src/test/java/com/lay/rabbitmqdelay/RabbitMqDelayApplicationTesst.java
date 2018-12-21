package com.lay.rabbitmqdelay;

import com.lay.rabbitmqdelay.config.QueueEnum;
import com.lay.rabbitmqdelay.provider.MessageProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 10:26 2018/12/21
 * @Modified By:IntelliJ IDEA
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitMqDelayApplication.class)
public class RabbitMqDelayApplicationTesst {

    @Autowired
    private MessageProvider messageProvider;

    @Test
    public void testDelay(){
        messageProvider.sendMessage("测试延迟消费信息，写入时间: "+new Date(),
                QueueEnum.MESSAGE_TTL_QUEUE.getExchange(),
                QueueEnum.MESSAGE_TTL_QUEUE.getRoutingKey(),
                10000);
    }
}
