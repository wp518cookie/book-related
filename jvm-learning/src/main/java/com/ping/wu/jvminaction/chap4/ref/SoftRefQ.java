package com.ping.wu.jvminaction.chap4.ref;

import com.alibaba.fastjson.JSON;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/**
 * @author wuping
 * @date 2020-03-25
 */

public class SoftRefQ {
    private static class User {
        public int id;
        public String name;
        public byte[] arr;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
            arr = new byte[1024 * 925 * 7];
        }

        @Override
        public String toString() {
            return "id: " + id + " ,name: " + name;
        }
    }

    static volatile ReferenceQueue<User> softQueue = null;
    public static class CheckRefQueue extends Thread {

        @Override
        public void run() {
            while (true) {
                if (softQueue != null) {
                    UserSoftReference obj = null;
                    try {
                        obj = (UserSoftReference) softQueue.remove();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (obj != null) {
                        System.out.println("user id " + obj.uid + " is delete");
                        try {
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static class UserSoftReference extends SoftReference<User> {
        int uid;
        byte[] arr;
        public UserSoftReference(User reference, ReferenceQueue<? super User> q) {
            super(reference, q);
            uid = reference.id;
            arr = new byte[1024 * 1024];
        }
    }

    public static void main(String[] args) throws Exception {
        Thread t = new CheckRefQueue();
        t.setDaemon(true);
        t.start();
        User u = new User(1, "wp");
        softQueue = new ReferenceQueue<>();
        UserSoftReference userSoftRef = new UserSoftReference(u, softQueue);
        u = null;
        System.out.println(userSoftRef.get());
        System.gc();

        System.out.println("After GC:");
        System.out.println(userSoftRef.get());
        System.out.println("try to create byte array and GC");
        byte[] b = new byte[1024 * 925 * 7];
        System.out.println("done");
        System.gc();
        System.out.println(userSoftRef.get());
        Thread.sleep(5000);
    }
}
