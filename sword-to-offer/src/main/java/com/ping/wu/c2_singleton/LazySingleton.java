package com.ping.wu.c2_singleton;

/**
 * @author wuping
 * @date 2018/12/15
 */

public class LazySingleton {
    private static LazySingleton singleton;

    private LazySingleton() {

    }

    public static synchronized LazySingleton getInstance() {
        if (singleton == null) {
            singleton = new LazySingleton();
        }
        return singleton;
    }
}
