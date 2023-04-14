package com.demo.threadpoolandcountdownlatch.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class CountDownLatchService {

    @Resource(name = "threadPoolServiceExecutor")
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    public void  countDownLatchTest() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            try {
                threadPoolTaskExecutor.execute(() -> {
                    log.info("线程名称：{}", Thread.currentThread().getName());
                });
        } catch(Exception e){
            log.error("执行失败,失败原因:{}",e.getMessage());
        }finally {
                //其中一个业务失败不影响其他业务执行
                countDownLatch.countDown();
            }
        }
        boolean await = countDownLatch.await(2000, TimeUnit.MILLISECONDS);
        log.info("await是否全部完成:{}", await);
        if (!await) log.error("指定时间未完成任务");
    }
}
