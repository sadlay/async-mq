package com.lay.scheduled.service.impl;

import com.lay.scheduled.service.ScheduleService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 17:43 2018/11/19
 * @Modified By:IntelliJ IDEA
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {
    //计数器
    int count1=1;
    int count2=1;

    //每隔一秒执行一次
    @Scheduled(initialDelay = 3000,fixedRate = 1000)
    //使用异步执行
    @Async
    @Override
    public void job1() {
        System.out.println("【"+Thread.currentThread().getName()+"】"+"【job1】每秒执行1次，执行第【"+count1+"】次");
        count1++;
    }

    //每隔一秒执行一次
    @Scheduled(cron = "0 05 18 ? * *")
    //使用异步执行
    @Async
    @Override
    public void job2() {
        System.out.println("【"+Thread.currentThread().getName()+"】"+"【job2】每秒执行1次，执行第【"+count2+"】次");
        count2++;
    }
}
