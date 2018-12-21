package com.lay.rabbitmqdelay.controller;

import com.lay.rabbitmqdelay.config.QueueEnum;
import com.lay.rabbitmqdelay.config.XdelayConfig;
import com.lay.rabbitmqdelay.provider.MessageProvider;
import com.lay.rabbitmqdelay.provider.XdelayProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 10:51 2018/12/21
 * @Modified By:IntelliJ IDEA
 */
@RestController
public class ProviderController {
    @Autowired
    MessageProvider messageProvider;
    @Autowired
    XdelayProvider xdelayProvider;

    @GetMapping("/rabbitmq/delay/{times}")
    public String delay(@PathVariable("times") long times){
        messageProvider.sendMessage("测试延迟消费信息，写入时间: "+new Date(),
                QueueEnum.MESSAGE_TTL_QUEUE.getExchange(),
                QueueEnum.MESSAGE_TTL_QUEUE.getRoutingKey(),
                times*1000);
        return "Success";
    }

    @GetMapping("/rabbitmq/xdelay/{times}")
    public String xDelay(@PathVariable("times") long times){
        xdelayProvider.sendXDelayMessage("测试x-delay延迟消费信息，写入时间: "+new Date(),
                XdelayConfig.X_DELAY_MESSAGE_EXCHANGE,
                XdelayConfig.X_DELAY_ROUTING_KEY,
                times*1000);
        return "X-Delay Message Send Success";
    }

}
