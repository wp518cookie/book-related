package com.ping.wu.jvm.classloader;

/**
 * @author wuping
 * @date 2019/4/11
 */

public class ClassTest {
    public static void main(String[] args) {
        if (ClassTest.class instanceof Class) {
            System.out.println("yessssss");
        }
    }
}
