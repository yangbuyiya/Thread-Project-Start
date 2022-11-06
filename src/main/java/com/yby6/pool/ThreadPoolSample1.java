package com.yby6.pool;

import java.util.LinkedList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 固定线程池
 */
public class ThreadPoolSample1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 固定线程总数 空闲程序用于执行任务 如果线程都在使用 后续任务则处于等待状态
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 100; i++) {
            // 执行runnable 线程
            int finalI = i;
            // 1. 不需要返回值的，使用execute方法来执行runnable
            executorService.execute(() -> {
                System.out.println(Thread.currentThread().getName() + ":" + finalI);
            });

            // 2. 需要返回值的，使用Callback对象，利用future 对象接收返回参数
            Future<String> receive = executorService.submit(() -> Thread.currentThread().getName() + ":" + finalI);
            System.out.println(receive.get());

        }

        // 关闭线程池
        executorService.shutdown();


    }


}
