package com.lay.scheduling.controller;

import com.lay.scheduling.pojo.User;
import com.lay.scheduling.service.ActiveMqService;
import com.lay.scheduling.service.ActiveMqUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 11:27 2018/11/19
 * @Modified By:IntelliJ IDEA
 */
@Controller
public class ActiveMqController {
    @Autowired
    private ActiveMqService activeMqService;

    @Autowired
    private ActiveMqUserService activeMqUserService;

    //测试普通消息的发送
    @ResponseBody
    @GetMapping("/msg")
    public Map<String,Object> msg(String message){
        activeMqService.sendMsg(message);
        return result(true,message);
    }

    @ResponseBody
    @GetMapping("/user")
    public Map<String,Object> sendUser(Long id,String userName,String note){
        User user=new User(id,userName,note);
        activeMqUserService.sendUser(user);
        return result(true,user);
    }

    private Map<String,Object> result(Boolean success,Object message){
        Map<String ,Object> result=new HashMap<>();
        result.put("success",success);
        result.put("message",message);
        return result;
    }
}
