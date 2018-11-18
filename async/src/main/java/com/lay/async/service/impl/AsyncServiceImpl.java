package com.lay.async.service.impl;

import com.lay.async.service.AsyncService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 23:02 2018/11/18
 * @Modified By:IntelliJ IDEA
 */
@Service
public class AsyncServiceImpl implements AsyncService {
    @Override
    @Async
    public void generateReport() {
        System.out.println("报表线程名称：【"+Thread.currentThread().getName()+"】");
    }
}
