package com.demo.threadpoolandcountdownlatch.threadPoolConfig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池
 * 提供打印出当前线程池状态的方法
 *
 * @author OiNK
 * @date 2023/04/14
 */
@Slf4j
public class AppThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {

    private static final long serialVersionUID = 1L;

    /**
     * 打印线程池状态日志
     */
    private void showThreadPoolInfo(String prefix) {
        log.info(prefix + " ----------------------");
        ThreadPoolExecutor threadPoolExecutor = getThreadPoolExecutor();
        log.info("{},{},taskCount[{}], completedTaskCount [{}], activeCount [{}], queueSize [{}]", this.getThreadNamePrefix(),

                prefix,

                threadPoolExecutor.getTaskCount(),

                threadPoolExecutor.getCompletedTaskCount(),

                threadPoolExecutor.getActiveCount(),

                threadPoolExecutor.getQueue().size());

    }

    @Override

    public void execute(Runnable task) {

        showThreadPoolInfo("execute");

        super.execute(task);

    }

    @Override

    public void execute(Runnable task, long startTimeout) {

        showThreadPoolInfo("execute with timeout");

        super.execute(task, startTimeout);

    }

    @Override

    public Future<?> submit(Runnable task) {

        showThreadPoolInfo("submit");

        return super.submit(task);

    }

    @Override

    public <T> Future<T> submit(Callable<T> task) {

        showThreadPoolInfo("submit callable");

        return super.submit(task);

    }

    @Override

    public ListenableFuture<?> submitListenable(Runnable task) {

        showThreadPoolInfo("submitListenable");

        return super.submitListenable(task);

    }

    @Override

    public <T> ListenableFuture<T> submitListenable(Callable<T> task) {

        showThreadPoolInfo("submitListenable callable");

        return super.submitListenable(task);

    }

}
