package com.ping.wu.jvm.volatiletest;

/**
 * @author wuping
 * @date 2019-05-14
 */

public class ThreadB extends Thread {
    
    @Override
    public void run() {
        ThreadA.a++;
        ThreadA.b++;
        ThreadA.c++;
    }
}
