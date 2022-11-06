package com.yby6.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 单线程池
 * 也就是说就一个线程来执行
 * 正常开发中用的少
 * 一般用于底层开发
 */
public class ThreadPoolSample3 {
    public static void main(String[] args) {
        // ExecutorService 管理线程池
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            executorService.execute(() -> System.out.println(Thread.currentThread().getName() + ":" + finalI));
        }
        executorService.shutdown();

    }
}
