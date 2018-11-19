package com.lay.scheduling.service;

/**
 * @Description: ActiveMQ服务接口
 * @Author: lay
 * @Date: Created in 10:55 2018/11/19
 * @Modified By:IntelliJ IDEA
 */

public interface ActiveMqService {

    //发送消息
    public void sendMsg(String message);

    //接收消息
    public void receiveMsg(String message);
}
