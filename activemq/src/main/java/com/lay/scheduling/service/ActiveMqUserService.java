package com.lay.scheduling.service;

import com.lay.scheduling.pojo.User;

/**
 * @Description: 传递对象服务
 * @Author: lay
 * @Date: Created in 11:20 2018/11/19
 * @Modified By:IntelliJ IDEA
 */
public interface ActiveMqUserService {
    public void sendUser(User user);

    public void receiveUser(User user);
}
