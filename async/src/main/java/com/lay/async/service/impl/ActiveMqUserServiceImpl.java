package com.lay.async.service.impl;


import com.lay.async.pojo.User;
import com.lay.async.service.ActiveMqUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * @Description: 发送对象服务类实现类
 * @Author: lay
 * @Date: Created in 11:22 2018/11/19
 * @Modified By:IntelliJ IDEA
 */
@Service
public class ActiveMqUserServiceImpl implements ActiveMqUserService {
    @Autowired
    private JmsTemplate jmsTemplate;

    private static final String MYDESTINATION = "my-destination";

    @Override
    public void sendUser(User user) {
        System.out.println("发送消息【" + user + "】");
        //使用自定义地址发送对象
        jmsTemplate.convertAndSend(MYDESTINATION,user);
    }

    @Override
    //监听自定义地址
    @JmsListener(destination = MYDESTINATION)
    public void receiveUser(User user) {
        System.out.println("接收到消息：【"+user+"】");

    }
}
