package com.ping.wu.jvminaction.chap7;

import java.nio.ByteBuffer;

/**
 * @author wuping
 * @date 2020-03-26
 */

public class DirectBufferOOM {
    public static void main(String[] args) {
        for (int i = 0; i < 1024; i++) {
            ByteBuffer.allocate(1024 * 1024);
            System.out.println(i);
        }
    }
}
