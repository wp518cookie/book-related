package com.ping.wu.c2_singleton;

/**
 * @author wuping
 * @date 2018/12/15
 */

public enum EnumSingleton {
    INSTANCE;

    private Object singleton;

    EnumSingleton() {
        singleton = new Object();
    }

    public Object getSingleton() {
        return singleton;
    }

    public static void main(String[] args) {
        Object o1 = EnumSingleton.INSTANCE.getSingleton();
        Object o2 = EnumSingleton.INSTANCE.getSingleton();
        System.out.println(o1 == o2);
    }
}
