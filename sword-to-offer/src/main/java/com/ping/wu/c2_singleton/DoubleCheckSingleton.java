package com.ping.wu.c2_singleton;

/**
 * @author wuping
 * @date 2018/12/15
 */

public class DoubleCheckSingleton {
    /**
     * volatile作用：禁止 JVM 的指令重排，
     * 由于重排序，3-2 别的线程可能会获取到一个尚未初始化的对象
     * 1、为 uniqueInstance 分配内存空间
     * 2、初始化 uniqueInstance
     * 3、将 uniqueInstance 指向分配的内存地址
     */
    private static volatile DoubleCheckSingleton instance;

    private DoubleCheckSingleton() {

    }

    public static DoubleCheckSingleton getSingleton() {
        if (instance == null) {
            synchronized (DoubleCheckSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckSingleton();
                }
            }
        }
        return instance;
    }
}
