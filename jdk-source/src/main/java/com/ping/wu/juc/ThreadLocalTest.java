package com.ping.wu.juc;

/**
 * @author wuping
 * @date 2020-06-12
 */

public class ThreadLocalTest {
    public static ThreadLocal<String> threadLocal1 = new ThreadLocal();
    public static ThreadLocal<Integer> threadLocal2 = new ThreadLocal();

    public static void main(String[] args) {
        ThreadLocalTest threadLocalTest = new ThreadLocalTest();
        Thread temp = new Thread(() -> {
           threadLocalTest.test();
        });
        temp.start();
    }

    private void test() {
        Thread t = Thread.currentThread();
        System.out.println(t.getId());
        threadLocal1.set("hello world");
        threadLocal2.set(100);
        System.out.println(threadLocal1.get());
        System.out.println(threadLocal2.get());
    }
}
