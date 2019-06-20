package com.ping.wu.jvm.gc;

/**
 * @author wuping
 * @date 2019/4/10
 * -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC
 *
 * [GC (Allocation Failure) [DefNew: 8056K->413K(9216K), 0.0056292 secs] 8056K->6557K(19456K), 0.0056612 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
 *
 */

public class HandlePromotionFailure {
    private static final int _1MB = 1024 * 1024;

    public static void testHandlePromotion() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB];
    }

    public static void main(String[] args) {
        testHandlePromotion();
    }
}
