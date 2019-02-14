package com.ping.wu.se.concurrent;

import java.util.concurrent.locks.LockSupport;

/**
 * @author wuping
 * @date 2019/2/11
 */

public class LockSupportTest {
    // 二元信号量，只能加到1
    public static void main(String[] args) {
        LockSupport.unpark(Thread.currentThread());
        LockSupport.unpark(Thread.currentThread());
        LockSupport.park();
        System.out.println("1");
        LockSupport.park();
        System.out.println(2);
    }
}
