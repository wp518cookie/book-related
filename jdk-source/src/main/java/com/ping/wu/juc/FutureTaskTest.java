package com.ping.wu.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author wuping
 * @date 2018/12/29
 */

public class FutureTaskTest {
    public static void main(String[] args) throws Exception {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {

                }
                return 3;
            }
        };
        java.util.concurrent.FutureTask<Integer> futureTask = new java.util.concurrent.FutureTask(callable);
        futureTask.run();
        System.out.println(futureTask.get());
    }
}
