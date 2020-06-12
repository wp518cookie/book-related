package com.ping.wu.jvminaction.chap3;

/**
 * @author wuping
 * @date 2020-03-25
 *  *
 *  -Xmx20m -Xms20m -Xmn1m   -XX:SurvivorRatio=2 -XX:+PrintGCDetails
 *  * -Xmx20m -Xms20m -Xmn7m   -XX:SurvivorRatio=2 -XX:+PrintGCDetails
 *  * -Xmx20m -Xms20m -Xmn15m  -XX:SurvivorRatio=8 -XX:+PrintGCDetails
 */

public class NewSizeDemo {
    public static void main(String[] args) throws Exception {
        byte[] b = null;
        for (int i = 0; i < 10; i++) {
            b = new byte[1 * 1024 * 1024];
        }
        Thread.sleep(1000000);
    }
}
