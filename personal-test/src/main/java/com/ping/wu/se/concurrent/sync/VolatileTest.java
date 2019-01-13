package com.ping.wu.se.concurrent.sync;

import org.apache.commons.lang3.RandomUtils;
import sun.misc.Contended;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author wuping
 * @date 2018/12/26
 */

public class VolatileTest {
    @Contended
    private volatile int value = 0;
    @Contended
    private int value2 = 0;

    private Object o = new Object();

    public void test() {
        int random = RandomUtils.nextInt();
        if (random != 2) {
            synchronized (o) {
                if (random != 2) {
                    value++;
                    value2++;
//                    countDownLatch.countDown();
                }
            }
        }
        if (value != value2) {
            System.out.println("error : value " + value + " , value2 : " + value2);
        }
    }

    public static void main(String[] args) throws Exception {
        VolatileTest test = new VolatileTest();

        ExecutorService pool = Executors.newFixedThreadPool(100);
        int num = 100000;
//        CountDownLatch countDownLatch = new CountDownLatch(num);
        for (int i = 0; i < num; i++) {
            pool.submit(()-> test.test());
        }
        TimeUnit.SECONDS.sleep(3);
        System.out.println("value : " + test.value);
        System.out.println("value2 : " + test.value2);
    }
}
