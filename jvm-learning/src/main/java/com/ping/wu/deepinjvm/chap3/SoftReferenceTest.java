package com.ping.wu.deepinjvm.chap3;

import java.lang.ref.ReferenceQueue;

/**
 * @author wuping
 * @date 2020-06-15
 */

public class SoftReferenceTest {
    static volatile ReferenceQueue<User> softQueue = new ReferenceQueue<>();

    public static void main(String[] args) throws Exception {
        Thread t = new CheckThread();
        t.setDaemon(true);
        t.start();
        MySoftReference mySoftReference = new MySoftReference(new User(1, "wp"), softQueue);
        System.out.println(mySoftReference.get());
        System.gc();

        System.out.println("After GC: " + mySoftReference.get());
        System.out.println(mySoftReference.get());
        System.out.println("try to create byte array and GC");
        byte[] b = new byte[1024 * 1024 * 7];
        System.out.println("done");
        System.out.println(mySoftReference.get());
        System.out.println(b.length);
        Thread.sleep(5000);
    }

    public static class CheckThread extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    MySoftReference mySoftReference = (MySoftReference) softQueue.remove();
                    System.out.println("softQueue removed:" + mySoftReference);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
