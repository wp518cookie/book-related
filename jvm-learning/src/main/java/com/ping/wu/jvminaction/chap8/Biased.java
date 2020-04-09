package com.ping.wu.jvminaction.chap8;

import org.openjdk.jol.info.ClassLayout;

import java.util.List;
import java.util.Vector;

/**
 * @author wuping
 * @date 2020-03-26
 *
 * 启用  353
 * -XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0 -client -Xmx512m -Xms512m
 *
 * 禁用   474
 * -XX:-UseBiasedLocking -client -Xms512m -Xmx512m
 */

public class Biased {
    public static List<Integer> numberList = new Vector();

    // 不用偏向锁594
    //
    public static void main(String[] args) throws InterruptedException {
        long begin = System.currentTimeMillis();
        int count = 0;
        int startnum = 0;
        while (count < 10000000) {
            numberList.add(startnum);
            startnum += 2;
            count++;
        }
        long end = System.currentTimeMillis();
        System.out.println(end - begin);
    }
}
