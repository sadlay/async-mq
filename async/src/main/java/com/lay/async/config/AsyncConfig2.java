package com.lay.async.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;


/**
 * log配置类
 * @Description TODO 
 * @ClassName   LogConfig 
 * @Date        2018年11月9日 下午2:40:38 
 * @Author      gs.zhao
 */
//@Configuration
//@EnableAsync
public class AsyncConfig2 {
    
    //异步任务池
    private AsyncTaskExecutor taskScheduler = null;
    

    
    /**
     * 创建异步线程池
     * @return
     * @Date        2018年11月14日 下午4:38:22 
     * @Author      gs.zhao
     */
    @Bean(name = "asyncExecutor")
    public AsyncTaskExecutor taskExecutor() {
        if (taskScheduler != null) {
            return taskScheduler;
        }
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("Anno-Executor");
        //核心线程池数
        executor.setCorePoolSize(10);
        //最大线程
        executor.setMaxPoolSize(30);
        //线程最大存活时间
        executor.setKeepAliveSeconds(300);
        //队列容量
        executor.setQueueCapacity(1000);
        //线程满后，线程被拒绝执行策略
        executor.setRejectedExecutionHandler(new java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy());
        this.taskScheduler = executor;
        return executor;
    }
    
}
