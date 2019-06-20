package com.ping.wu.jvm.gc;

/**
 * @author wuping
 * @date 2019/4/10
 */

public class AllocEden {
    public static final int _1K = 1024;

    public static void main(String[] args) {
        for (int i = 0; i < 5 * _1K; i++) {
            byte[] b = new byte[_1K];
        }
    }
}
