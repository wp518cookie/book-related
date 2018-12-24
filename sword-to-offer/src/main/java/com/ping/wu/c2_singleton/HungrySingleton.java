package com.ping.wu.c2_singleton;

/**
 * @author wuping
 * @date 2018/12/15
 */

public class HungrySingleton {
    private static HungrySingleton singleton = new HungrySingleton();
    private HungrySingleton() {

    }

    private static HungrySingleton getInstance() {
        return singleton;
    }
}
