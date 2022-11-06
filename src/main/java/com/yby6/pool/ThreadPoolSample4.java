package com.yby6.pool;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 定时线程池
 */
public class ThreadPoolSample4 {
    public static void main(String[] args) {
        // 调度线程池1
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
        for (int i = 0; i < 100; i++) {
            // 延迟1秒开始执行  每隔3秒执行一次
            executorService.scheduleAtFixedRate(() -> {
                System.out.println(new Date() + "延迟1秒开始执行  每隔3秒执行一次");
            }, 1, 3, TimeUnit.SECONDS);
        }
    }
}
