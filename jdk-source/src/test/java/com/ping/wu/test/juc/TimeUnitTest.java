package com.ping.wu.test.juc;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author wuping
 * @date 2019/2/23
 */

public class TimeUnitTest {
    public static void main(String[] args) {
        long val1 = TimeUnit.DAYS.toMillis(30);
        long val2 = 30l * 24 * 60 * 60 * 1000;
        System.out.println(val1);
        System.out.println(val2);
        long val = System.currentTimeMillis() - val1;
        Date d = new Date(val);
        System.out.println(d.getTime());
        System.out.println(val);
    }
}
