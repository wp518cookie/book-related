package com.ping.wu.jvm.volatiletest;

/**
 * @author wuping
 * @date 2019-05-14
 */

public class ThreadA extends Thread {
    public static int a = 0;
    public static volatile int b = 0;
    public static long[] test1 = new long[]{1,2,3,4,5,6,7,8};
    public static int c = 0;
    private Thread threadB;

    public ThreadA(ThreadB threadB) {
        this.threadB = threadB;
    }

    @Override
    public void run() {
        try {
            threadB.join();
            System.out.println("a:" + a + ", b:" + b + ", c:" + c);
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {
            ThreadB threadB = new ThreadB();
            ThreadA threadA = new ThreadA(threadB);
            threadA.start();
            threadB.start();
    }
}
