package com.ping.wu.juc;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author wuping
 * @date 2020-08-31
 * https://mahmoudanouti.wordpress.com/2018/01/26/20-examples-of-using-javas-completablefuture/
 */

public class CompletableFutureTest {
    private static ExecutorService executor = Executors.newFixedThreadPool(10);

    @Test
    public void test1() throws Exception {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("test async");
            return "hello world";
        }, executor);
        System.out.println("CompletableFutureTest:" + completableFuture.get());
    }

    @Test
    public void testSerial() throws Exception {
        CompletableFuture<String> f0 = CompletableFuture.supplyAsync(
                () -> "hello"
        ).thenApply(s -> s + " world").thenApply(String::toUpperCase);
        System.out.println(f0.join());
    }

    @Test
    public void testAnd() throws Exception {
        CompletableFuture<Void> f1 = CompletableFuture.runAsync(() -> {
            System.out.println("异步任务-1");
        });
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            String s = "异步任务-2";
            System.out.println(s);
            return s;
        });
        CompletableFuture<String> f3 = f1.thenCombine(f2, (a, tf) -> {
            return tf;
        });
        System.out.println(f3.join());
    }

    @Test
    public void testException() throws Exception {
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " supplyAsync");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {

            }
            throw new IllegalArgumentException("参数不对");
        }, executor);
        f1.handleAsync((s, th) -> {
            System.out.println(Thread.currentThread().getName() + " handle");
            if (th != null) {
                System.out.println("任务处理异常:" + th.getMessage());
                return null;
            } else {
                return s;
            }
        }, executor);
        System.out.println(Thread.currentThread().getName() + "主线程执行完毕");
        TimeUnit.SECONDS.sleep(5);
    }

    @Test
    public void testExceptional() throws Exception {
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("任务开始执行");
                TimeUnit.SECONDS.sleep(3);
                System.out.println("任务执行完毕");
            } catch (Exception e) {

            }
            return "hello world";
        });
        TimeUnit.SECONDS.sleep(1);
        System.out.println(f1.cancel(true));
        System.out.println(f1.join());
    }
}
