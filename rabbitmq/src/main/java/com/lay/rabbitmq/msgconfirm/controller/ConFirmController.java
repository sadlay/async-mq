package com.lay.rabbitmq.msgconfirm.controller;

import com.lay.rabbitmq.msgconfirm.sender.ConfirmSender;
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
    private ConfirmSender confirmSender;

    @GetMapping("/rabbitmq/confirm")
    public String confirm(){
        confirmSender.send();
        return "成功";
    }
}
