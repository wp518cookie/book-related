package com.ping.wu.singleton;

/**
 * @author wuping
 * @date 2019-09-25
 */

public class Singleton1 {
    private Singleton1() {

    }

    private static class Inner {
        private static Singleton1 singleton1 = new Singleton1();
    }

    public static Singleton1 getSingleton() {
        return Inner.singleton1;
    }
}
