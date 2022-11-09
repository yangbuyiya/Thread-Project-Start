package com.yby6.completableFuture;

import org.junit.Test;

import java.util.Map;
import java.util.concurrent.*;

/**
 * 并行 执行 节省时间
 */
public class CompletableFutureTest {
    ExecutorService pool = Executors.newFixedThreadPool(3);

    @Test
    public void t() {
        Map<String, Object> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("result1", result1());
        concurrentHashMap.put("result2", result2());
        concurrentHashMap.put("result3", result3());
        System.out.println(concurrentHashMap);
    }

    @Test
    public void t1() {
        Map<String, Object> concurrentHashMap = new ConcurrentHashMap<>();
        // 并行
        CompletableFuture.allOf(
                CompletableFuture.runAsync(() -> concurrentHashMap.put("result1", result1()), pool),
                CompletableFuture.runAsync(() -> concurrentHashMap.put("result2", result2()), pool),
                CompletableFuture.runAsync(() -> concurrentHashMap.put("result3", result3()), pool))
                .join(); // 合并结果

        pool.execute(()-> System.out.println(concurrentHashMap));

    }

    private static Object result1() {

        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return "结果1";
    }

    private static Object result2() {

        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return "结果2";
    }

    private static Object result3() {

        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return "结果3";
    }
}
