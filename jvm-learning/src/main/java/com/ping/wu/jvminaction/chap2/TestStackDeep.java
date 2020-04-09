package com.ping.wu.jvminaction.chap2;

/**
 * @author wuping
 * @date 2020-03-20
 */

public class TestStackDeep {
    private static int count = 0;

    public static void recursion() {
        count++;
        recursion();
    }

    public static void main(String[] args) {
        try {
            recursion();
        } catch (Throwable e) {
            System.out.println("deep of calling = " + count);
            e.printStackTrace();
        }
    }
}
