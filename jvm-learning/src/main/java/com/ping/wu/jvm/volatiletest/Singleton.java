package com.ping.wu.jvm.volatiletest;

/**
 * @author wuping
 * @date 2019-04-16
 * -XX:+PrintAssembly
 */

public class Singleton {
    private volatile static Singleton instance;

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        Singleton.getInstance();
    }
}
