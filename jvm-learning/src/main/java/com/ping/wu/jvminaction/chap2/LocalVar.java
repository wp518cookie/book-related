package com.ping.wu.jvminaction.chap2;

/**
 * @author wuping
 * @date 2020-03-20
 */

public class LocalVar {
    public void localvar1() {
        int a = 0;
        System.out.println(a);
        int b = 0;
    }

    public void localvar2() {
        {
            int a = 0;
            System.out.println(a);
        }
        int b = 0;
    }

    public static void main(String[] args) {

    }
}
