package com.lay.rabbitmqdelay;

import com.lay.rabbitmqdelay.config.QueueEnum;
import com.lay.rabbitmqdelay.config.XdelayConfig;
import com.lay.rabbitmqdelay.provider.MessageProvider;
import com.lay.rabbitmqdelay.provider.XdelayProvider;
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
public class RabbitMqXDelayApplicationTesst {

    @Autowired
    private XdelayProvider xdelayProvider;

    @Test
    public void testDelay(){
        xdelayProvider.sendXDelayMessage("测试x-delay延迟消费信息，写入时间: "+new Date(),
                XdelayConfig.X_DELAY_MESSAGE_EXCHANGE,
                XdelayConfig.X_DELAY_ROUTING_KEY,
                10000);
    }
}
