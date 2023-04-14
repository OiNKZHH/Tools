package com.demo;

import com.demo.threadpoolandcountdownlatch.service.CountDownLatchService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class ThreadPoolAndCountDownLatchApplicationTests {


    @Autowired
    private CountDownLatchService countDownLatchService;

    @Test
    public void  countDownLatchTest() throws InterruptedException {
        countDownLatchService.countDownLatchTest();
    }
}
