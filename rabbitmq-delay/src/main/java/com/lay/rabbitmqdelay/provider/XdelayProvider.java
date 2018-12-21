package com.lay.rabbitmqdelay.provider;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 13:32 2018/12/21
 * @Modified By:IntelliJ IDEA
 */
@Component
public class XdelayProvider {

    private static final Logger log= LoggerFactory.getLogger(XdelayProvider.class);
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendXDelayMessage(Object messageContent,String exchange,String routingKey,final long delayTimes){
        if(!StringUtils.isEmpty(exchange)){
            log.info("延迟: {} 毫秒写入x-delay消息队列：{}，消息内容：{}",delayTimes,routingKey, JSON.toJSONString(messageContent));
            //执行发送消息到指定队列
            rabbitTemplate.convertAndSend(exchange,routingKey,messageContent,message -> {
                //设置延迟毫秒值
                message.getMessageProperties().setDelay((int) delayTimes);
                return message;
            });
        }else{
            log.error("未找到队列消息：{}，所属的交换机",exchange);
        }
    }
}
