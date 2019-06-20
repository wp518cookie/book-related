package com.ping.wu.jvm.classloader;

/**
 * @author wuping
 * @date 2019/4/11
 * -XX:+TraceClassLoading
 */

public class ClassLoadTest2 {
    private static class Parent {
        static {
            System.out.println("Parent init");
        }
        public static int v = 100;
    }

    /**
     * child也被加载了，只是未被初始化
     */
    private static class Child extends Parent {
        static {
            System.out.println("Child init");
        }
    }

    public static void main(String[] args) {
//        System.out.println(Child.v);
        Child parent = new Child();
        System.out.println(parent instanceof Parent);
    }
}
