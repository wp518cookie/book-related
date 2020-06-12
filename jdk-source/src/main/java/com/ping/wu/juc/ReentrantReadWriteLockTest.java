package com.ping.wu.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author wuping
 * @date 2020-05-27
 */

public class ReentrantReadWriteLockTest {
    static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    public final static Lock readLock = readWriteLock.readLock();
    public final static Lock writeLock = readWriteLock.writeLock();

    public final static List<String> data = new ArrayList();

    public static void main(String[] args) {
        new Thread(() -> write()).start();
        new Thread(() -> read()).start();
    }

    public static void write() {
        try {
            writeLock.lock();
            data.add("写数据");
            System.out.println(Thread.currentThread().getName() + ":写数据");
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {

        } finally {
            writeLock.unlock();
        }
    }

    public static void read() {
        try {
            readLock.lock();
            for (String meta : data) {
                System.out.println(Thread.currentThread().getName() + ":读取数据:" + meta);
            }
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {

        } finally {
            readLock.unlock();
        }
    }
}
