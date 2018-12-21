package com.lay.rabbitmqdelay.provider;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Queue;
import java.util.concurrent.DelayQueue;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 10:09 2018/12/21
 * @Modified By:IntelliJ IDEA
 */
@Component
public class MessageProvider {
    private static final Logger log= LoggerFactory.getLogger(MessageProvider.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //发送延迟消息
    public void sendMessage(Object messageContent,String exchange,String routingKey,final long delayTimes){
        if(!StringUtils.isEmpty(exchange)){
            log.info("延迟: {}毫秒写入消息队列：{}，消息内容：{}",delayTimes,routingKey, JSON.toJSONString(messageContent));
            //执行发送消息到指定队列
            rabbitTemplate.convertAndSend(exchange,routingKey,messageContent,message -> {
                //设置延迟毫秒值
                message.getMessageProperties().setExpiration(String.valueOf(delayTimes));
                return message;
            });
        }else{
            log.error("未找到队列消息：{}，所属的交换机",exchange);
        }
    }
}
