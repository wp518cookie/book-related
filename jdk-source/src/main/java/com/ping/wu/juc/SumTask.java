package com.ping.wu.juc;

import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * @author wuping
 * @date 2020-03-30
 */

public class SumTask extends RecursiveTask<Long> {
    private long start = 0;
    private long end = 0;

    public SumTask(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if (end - start < 10000) {
            long sumResult = 0;
            for (long i = start; i <= end; i++) {
               sumResult += i;
            }
            return sumResult;
        } else {
            long middle = (end + start) / 2;
            SumTask leftSum = new SumTask(start, middle);
            SumTask rightSum = new SumTask(middle + 1, end);
            leftSum.fork();
            rightSum.fork();
            Long leftResult = leftSum.join();
            Long rightResult = rightSum.join();
            return leftResult + rightResult;
        }
    }

    public static void main(String[] args) {
        SumTask sumTask = new SumTask(1, 10000000000L);
        sumTask.fork();
        long start = System.currentTimeMillis();
        System.out.println("result:" + sumTask.join());
        long end = System.currentTimeMillis();
        System.out.println("cost: " + (end - start));
        start = System.currentTimeMillis();
        long sumResult = 0;
        for (long i = 1; i <= 10000000000L; i++) {
            sumResult += i;
        }
        end = System.currentTimeMillis();
        System.out.println("result:" + sumResult);
        System.out.println("cost: " + (end - start));

        start = System.currentTimeMillis();
        Long sum = LongStream.rangeClosed(0L, 10000000000L).parallel().sum();
        end = System.currentTimeMillis();
        System.out.println("result:" + sum);
        System.out.println("cost: " + (end - start));
    }
}
