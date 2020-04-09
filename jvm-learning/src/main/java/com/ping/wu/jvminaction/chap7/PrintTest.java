package com.ping.wu.jvminaction.chap7;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author wuping
 * @date 2020-03-26
 */

public class PrintTest {
    public static void main(String[] args) throws Exception {
//        Thread.sleep(4000);
//        Person person = new Person();
//        System.out.println(ClassLayout.parseInstance(person).toPrintable());
        // 偏向锁
        /**
         * 05 68 00 11 (00000101 01101000 00000000 00010001) (285239301)  记录了持有该偏向锁的线程id
         */
        Thread.sleep(5000);
        Person a = new Person();
        synchronized (a){
            System.out.println(ClassLayout.parseInstance(a).toPrintable());
        }
    }
}
