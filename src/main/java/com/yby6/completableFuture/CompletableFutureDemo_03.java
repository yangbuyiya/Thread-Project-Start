package com.yby6.completableFuture;


import org.junit.Test;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureDemo_03 {

    @Test
    public void t1 () {
        CompletableFuture.allOf(
                CompletableFuture.supplyAsync(() -> result1(1500)),
                CompletableFuture.supplyAsync(() -> result1(700)),
                CompletableFuture.supplyAsync(() -> result1(700))
        ).join();


        SmallTool.printTimeAndThread("打印...");

    }


    private static Object result1(int time) {

        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return "结果1";
    }

}
