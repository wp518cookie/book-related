package com.ping.wu.deepinjvm.chap3;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author wuping
 * @date 2020-06-15
 */

public class WeakReferenceTest {
    public static ThreadLocal<String> threadLocal = new ThreadLocal();
    public static void main(String[] args) throws Exception {
        threadLocal.set("hello world");
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("start gc");
                System.gc();
            } catch (InterruptedException e) {

            }
        }).start();
        System.out.println(threadLocal.get());
        threadLocal = null;
        TimeUnit.SECONDS.sleep(3);
        System.out.println(threadLocal.get());
    }
}
