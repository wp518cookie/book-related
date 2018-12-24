package com.ping.wu.c2_singleton;

/**
 * @author wuping
 * @date 2018/12/15
 */

public class InnerClassSingleton {
    private static InnerClassSingleton singleton;

    private InnerClassSingleton() {

    }

    private static class InnerClass {
        private static InnerClassSingleton singleton = new InnerClassSingleton();
    }

    public static InnerClassSingleton getSingleton() {
        return InnerClass.singleton;
    }
}
