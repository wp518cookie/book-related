package com.ping.wu.test.juc;

import com.ping.wu.juc.MyCyclicBarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.TimeUnit;

/**
 * @author wuping
 * @date 2019/2/20
 */

public class MyCyclicBarrierTest {
    public static void main(String[] args) throws Exception {
        MyCyclicBarrier cyclicBarrier = new MyCyclicBarrier(5);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                String taskId = i + "_" + j;
                new Thread(new Task(taskId, cyclicBarrier)).start();
            }
            cyclicBarrier.await();
            System.out.println("大部队已通过，开启新一轮冲锋");
        }
    }

    private static class Task implements Runnable {
        private String taskId;
        private MyCyclicBarrier cyclicBarrier;

        Task(String taskId, MyCyclicBarrier cyclicBarrier) {
            this.taskId = taskId;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                Random random = new Random();
                int s = random.nextInt(3);
                TimeUnit.SECONDS.sleep(s);
                System.out.println("taskId : " + taskId + " 已经完成任务，等待大部队ing");
                cyclicBarrier.await();
            } catch (InterruptedException  e) {
                System.out.println("exception : " + e.getMessage());
            }
        }
    }
}
