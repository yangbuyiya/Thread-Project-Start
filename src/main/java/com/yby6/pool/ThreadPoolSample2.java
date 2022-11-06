package com.yby6.pool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 缓存线程池
 * 可缓存线程池的特点是 无限大
 * 如果线程池当中没有可以用的线程则自动创建新的线程来执行， 有空闲则利用起来
 */
public class ThreadPoolSample2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 调度器对象
        // ExecutorService 管理线程池

        ExecutorService executorService = Executors.newCachedThreadPool();
        // 可缓存线程池的特点是 无限大 如果线程池当中没有可以用的线程则自动创建新的线程来执行， 有空闲则利用起来
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            executorService.execute(() -> System.out.println(Thread.currentThread().getName() + ":" + finalI));
        }
        executorService.shutdown();
    }

}
