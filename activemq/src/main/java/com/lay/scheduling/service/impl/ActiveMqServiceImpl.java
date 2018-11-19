package com.lay.scheduling.service.impl;

import com.lay.scheduling.service.ActiveMqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * @Description:ActiveMQ服务类实现
 * @Author: lay
 * @Date: Created in 10:57 2018/11/19
 * @Modified By:IntelliJ IDEA
 */
@Service
public class ActiveMqServiceImpl implements ActiveMqService {
    //注入由spring boot 自动生成的jmsTemplate
    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public void sendMsg(String message) {
        System.out.println("发送消息【"+message+"】");
        jmsTemplate.convertAndSend(message);
        //自定义发送地址
        //jmsTemplate.convertAndSend("your-dastination",message);
    }

    @Override
    //使用注解，监听地址发过来的消息
    @JmsListener(destination = "${spring.jms.template.default-destination}",containerFactory = "jmsTopicListener")
    public void receiveMsg(String message) {
        System.out.println("接收到消息【"+message+"】");
    }
}
