package com.ping.wu.deepinjvm.chap2;

/**
 * @author wuping
 * @date 2020-06-11
 */

public class StackOverflowTest {
    public static void main(String[] args) {
        while (true) {
            test();
        }
    }

    public static void test() {
        System.out.println(123);
        test();
    }
}
