package com.ping.wu.jvm.gc;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wuping
 * @date 2019/4/10
 *
 * -Xmx32m -Xms32m -XX:+UseSerialGC -XX:+PrintGCDetails
 *
 * -Xmx32m -Xms32m -XX:+UseSerialGC -XX:+PrintGCDetails -XX:PretenureSizeThreshold=1000     会在TLAB分配
 *
 * -Xmx32m -Xms32m -XX:+UseSerialGC -XX:+PrintGCDetails -XX:PretenureSizeThreshold=1000 -XX:-UseTLAB
 */

public class PretenureSizeThreshold {
    public static final int _1K = 1024;

    public static void main(String[] args) {
        Map<Integer, byte[]> map = new HashMap();
        for (int i = 0; i < 5 * _1K; i++) {
            byte[] b = new byte[_1K];
            map.put(i, b);
        }
    }
}
