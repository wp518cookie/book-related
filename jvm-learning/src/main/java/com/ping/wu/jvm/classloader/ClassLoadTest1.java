package com.ping.wu.jvm.classloader;

/**
 * @author wuping
 * @date 2019/4/11
 * -XX:+TraceClassLoading
 */

public class ClassLoadTest1 {
    private static class Parent {
        static {
            System.out.println("Parent init");
        }
    }

    private static class Child extends Parent {
        static {
            System.out.println("Child init");
        }
    }

    public static void main(String[] args) {
        Parent child = new Child();
    }
}
