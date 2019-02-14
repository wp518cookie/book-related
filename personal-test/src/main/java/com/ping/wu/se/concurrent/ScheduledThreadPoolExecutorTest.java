package com.ping.wu.se.concurrent;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author wuping
 * @date 2019/2/12
 */

public class ScheduledThreadPoolExecutorTest {
    public static void main(String[] args) {
        ScheduledThreadPoolExecutor stpe = new ScheduledThreadPoolExecutor(1);
        stpe.scheduleAtFixedRate(() ->
                        System.out.println("hello world")
                , 0, 1000, TimeUnit.MILLISECONDS);
    }
}
