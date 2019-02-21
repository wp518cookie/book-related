package com.ping.wu.test.juc;

import com.ping.wu.juc.Semaphore;

//import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author wuping
 * @date 2019/2/20
 */

public class SemaphoreTest {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 10; i++) {
            new Thread(new Task(semaphore, i)).start();
        }
    }

    private static class Task implements Runnable {
        private Semaphore semaphore;
        private int taskId;

        Task(Semaphore semaphore, int taskId) {
            this.semaphore = semaphore;
            this.taskId = taskId;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                try {
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("taskId: " + taskId + " 执行完成!");
                } finally {
                    semaphore.release();
                }
            } catch (InterruptedException e) {

            }
        }
    }
}
