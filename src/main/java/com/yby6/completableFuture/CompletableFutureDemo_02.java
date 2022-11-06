package com.yby6.completableFuture;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 进阶
 */
public class CompletableFutureDemo_02 {

    /**
     * 那个先执行就使用哪个线程返回的结果
     */
    @Test
    public void t1() {
        SmallTool.printTimeAndThread("张三走出餐厅，来到公交车站。");
        SmallTool.printTimeAndThread("等待 700路 或者 800路 公交车到来");

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("700正在到来，等待红绿灯");
            SmallTool.sleepMillis(300);
            return "700路到了";
        }).applyToEither(CompletableFuture.supplyAsync(() -> {

            SmallTool.printTimeAndThread("800正在到来，等待红绿灯");
            SmallTool.sleepMillis(200);
            return "800路到了";
        }), firstComeBus -> firstComeBus);

        SmallTool.printTimeAndThread(String.format("%s,张三坐车回家", completableFuture.join()));


    }


    /**
     * 新增需求： 咣当公交车运行时 撞到树上了
     */
    @Test
    public void t2() {
        SmallTool.printTimeAndThread("张三走出餐厅，来到公交车站。");
        SmallTool.printTimeAndThread("等待 700路 或者 800路 公交车到来");

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("700正在到来，等待红绿灯");
            SmallTool.sleepMillis(300);
            return "700路到了";
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("800正在到来，等待红绿灯");
            SmallTool.sleepMillis(200);
            return "800路到了";
        }), firstComeBus -> {
            SmallTool.printTimeAndThread(firstComeBus);
            if (firstComeBus.startsWith("700")) {
                throw new RuntimeException("撞树了");
            }
            return firstComeBus;
        }).exceptionally(e -> {
            SmallTool.printTimeAndThread(e.getMessage());
            SmallTool.printTimeAndThread("张三叫网约车...");
            return "网约车,叫到了";
        });

        SmallTool.printTimeAndThread(String.format("%s,张三坐车回家", completableFuture.join()));


    }

}

