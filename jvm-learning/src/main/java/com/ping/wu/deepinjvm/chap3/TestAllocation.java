package com.ping.wu.deepinjvm.chap3;

/**
 * @author wuping
 * @date 2020-06-16
 * -verbose:gc -Xms20m -Xmx20m -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 */

public class TestAllocation {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB];
    }
}
