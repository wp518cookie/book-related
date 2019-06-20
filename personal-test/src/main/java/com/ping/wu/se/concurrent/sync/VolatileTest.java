package com.ping.wu.se.concurrent.sync;

import org.apache.commons.lang3.RandomUtils;
import sun.misc.Contended;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author wuping
 * @date 2018/12/26
 */

public class VolatileTest {
     private static volatile int a;

    public static void main(String[] args) {
        test(a);
    }

    public static void test(int a) {

    }
}
