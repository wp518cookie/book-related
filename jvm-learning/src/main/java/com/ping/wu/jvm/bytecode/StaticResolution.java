package com.ping.wu.jvm.bytecode;

/**
 * @author wuping
 * @date 2019-04-15
 */

public class StaticResolution {
    public static void sayHello() {
        System.out.println("hello world");
    }

    public static void main(String[] args) {
        StaticResolution.sayHello();
    }
}
