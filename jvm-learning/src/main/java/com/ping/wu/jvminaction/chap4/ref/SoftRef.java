package com.ping.wu.jvminaction.chap4.ref;

import java.lang.ref.SoftReference;

/**
 * @author wuping
 * @date 2020-03-25
 */

public class SoftRef {
    private static class User {
        public int id;
        public String name;
        public byte[] arr;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
            this.arr = new byte[1024 * 925 * 7];
        }

        @Override
        public String toString() {
            return "[id=" + id + ",name=" + name + "]";
        }
    }

    public static void main(String[] args) {
        User u = new User(1, "robin");
        SoftReference<User> userSoftRef = new SoftReference(u);
        u = null;
        System.out.println(userSoftRef.get());
        System.gc();
        System.out.println("After GC:");
        System.out.println(userSoftRef.get());
        byte[] b = new byte[1024 * 925 * 7];
        System.gc();
        System.out.println(userSoftRef.get());
    }
}
