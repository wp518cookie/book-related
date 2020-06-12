package com.ping.wu.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;

/**
 * @author wuping
 * @date 2020-05-28
 */

public class ReentrantReadWriteLockTest2 {
    private final static ReentrantReadWriteLock readAndWriteLock = new ReentrantReadWriteLock(true);

    private final static Lock readLock = readAndWriteLock.readLock();
    private final static Lock writeLock = readAndWriteLock.writeLock();

    private Map<String, String> map = new HashMap();

    public static void main(String[] args) {
        ReentrantReadWriteLockTest2 test = new ReentrantReadWriteLockTest2();
        for (int i = 0; i < 4; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "启动");
                test.writeAndRead();
            }).start();
        }
    }

    public void writeAndRead() {
//        System.out.println(Thread.currentThread().getName() + "请求获取写锁");
        writeLock.lock();
//        System.out.println(Thread.currentThread().getName() + "请求获取写锁成功");
        String readResult = map.get("a");
        if (readResult == null) {
            readLock.lock();
            System.out.println("读锁加成功了");
            map.put("a", "hello world");
            System.out.println(Thread.currentThread().getName() + "写完了数据");
            readLock.unlock();
            readResult = map.get("a");
        }
        System.out.println("读取的数据是:" + readResult);
        writeLock.unlock();
        System.out.println(Thread.currentThread().getName() + "执行完毕");
    }
}
