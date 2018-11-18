package com.lay.async.controller;

import com.lay.async.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 23:04 2018/11/18
 * @Modified By:IntelliJ IDEA
 */
@Controller
public class AsyncController {
    //注入异步服务
    @Autowired
    private AsyncService asyncService;

    @RequestMapping("/async")
    @ResponseBody
    public String asyncPage(){
        System.out.println("请求线程名称：【"+Thread.currentThread().getName()+"】");
        //异步调用服务
        asyncService.generateReport();
        return "async";
    }

}
