package com.lay.rabbitmqtwo.controller;

import com.lay.rabbitmqtwo.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 11:23 2018/12/20
 * @Modified By:IntelliJ IDEA
 */
@RestController
public class ConFirmController {
    @Autowired
    private Producer producer;

    @GetMapping("/rabbitmq/confirm")
    public String confirm(){
        for(int i=0;i<5000;i++) {
            producer.send();
        }
        return "成功";
    }

    @GetMapping("/rabbitmq/confirmUser")
    public String confirmUser(){
        for(int i=0;i<5000;i++) {
            producer.sendUser(i);
        }
        return "成功";
    }

}
