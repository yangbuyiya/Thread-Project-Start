package com.yby6.completableFuture;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 小白进入餐厅
 * 小白点了 xxx + xxx
 * 小白打游戏
 * 厨师炒菜
 * 服务员打饭
 * 做好了， 小白开吃
 */
public class CompletableFutureDemo_01 {

    @Test
    public void t1() {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("小白接入餐厅点菜");
            SmallTool.sleepMillis(200);
            SmallTool.printTimeAndThread("点了葱爆牛肉");
            SmallTool.sleepMillis(200);
            SmallTool.printTimeAndThread("厨师炒菜");
            SmallTool.sleepMillis(200);
            SmallTool.printTimeAndThread("厨师打饭");
            SmallTool.sleepMillis(200);
            return "葱爆牛肉做好了";
        });

        SmallTool.printTimeAndThread("小白在打王者");
        SmallTool.printTimeAndThread(String.format("%s,小白开吃", completableFuture.join()));
    }

    /**
     * 将厨师炒菜 和 服务员打饭 一起执行  并行
     * 优雅写法
     */
    @Test
    public void t3() {
        ExecutorService pool = Executors.newCachedThreadPool();

        SmallTool.printTimeAndThread("小白接入餐厅点菜");
        SmallTool.printTimeAndThread("点了葱爆牛肉");

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            SmallTool.sleepMillis(200);
            SmallTool.printTimeAndThread("厨师炒菜");
            return "葱爆牛肉做好了";
        }).thenCombineAsync(CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("服务员蒸饭");
            return "米饭";
        }), (val1, val2) -> { // 新线程
            SmallTool.printTimeAndThread("服务员打饭");
            return String.format("%s + %s", val1, val2);
        }, pool);

        SmallTool.printTimeAndThread("小白在打王者");
        SmallTool.printTimeAndThread(String.format("%s,小白开吃", completableFuture.join()));
    }

}
