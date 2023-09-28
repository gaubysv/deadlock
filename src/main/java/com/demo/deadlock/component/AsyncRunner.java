package com.demo.deadlock.component;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncRunner {

    @Async("threadPoolTaskExecutor")
    public void run(Runnable runnable) {
        runnable.run();
    }
}
