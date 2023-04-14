package com.demo.threadpoolandcountdownlatch.threadPoolConfig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池配置
 *
 * @author OiNK
 * @date 2023/04/14
 */
@Slf4j
@Configuration
@EnableAsync
public class ExecutorConfig {

    /**
     * 线程名称前缀
     */
    private static final String THREAD_NAME_PRIORITY = "app-thread-service-";

    /**
     * 核心线程数
     */
    @Value("${spring.threadPool.corePoolSize}")
    private int corePoolSize;

    /**
     * 最大线程数
     */
    @Value("${spring.threadPool.maxPoolSize}")
    private int maxPoolSize;

    /**
     * 队列长度
     */
    @Value("${spring.threadPool.queueCapacity}")
    private int queueCapacity;


    /**
     * 线程存活时长
     */
    @Value("${spring.threadPool.keepAliveSeconds}")
    private int keepAliveSeconds;


    @Bean
    public ThreadPoolTaskExecutor threadPoolServiceExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //核心线程数
        executor.setCorePoolSize(corePoolSize);
        //最大线程数
        executor.setMaxPoolSize(maxPoolSize);
        //线程池队列大小
        executor.setQueueCapacity(queueCapacity);
        //线程存活时长
        executor.setKeepAliveSeconds(keepAliveSeconds);
        //配置线程池中的线程名称前缀
        executor.setThreadNamePrefix(THREAD_NAME_PRIORITY);
        //当线程池maxPoolSize满了,采取由当前调用者所在的线程处理任务
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //初始化线程池
        executor.initialize();
        log.info("thread pool executor initialized");
        return executor;
    }

}

