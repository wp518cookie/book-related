package com.ping.wu.jvm.gc;

/**
 * @author wuping
 * @date 2019-04-13
 */

public class SlotTest {
    public static void main(String[] args) {
//        {
            byte[] placeholder = new byte[64 * 1024 * 1024];
//        }
        int a = 0;
        System.gc();
    }
}
