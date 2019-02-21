package com.ping.wu.test.juc;

import com.ping.wu.juc.CountDownLatch;

import java.util.concurrent.TimeUnit;

/**
 * @author wuping
 * @date 2019/2/18
 */

public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(new Task(latch)).start();
        }
        latch.await();
        System.out.println("main done");
    }

    private static class Task implements Runnable {
        private CountDownLatch latch;

        public Task(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {

            }
            System.out.println("任务完成! -------");
            latch.countDown();
        }
    }
}
