package com.ping.wu.jvminaction.chap7;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author wuping
 * @date 2020-03-26
 */

public class LightWeightTest {
    public static void main(String[] args) throws Exception {
        Thread.sleep(5000);
        Person a = new Person();

        Thread thread1 = new Thread(){
            @Override
            public void run() {
                synchronized (a){
                    System.out.println("thread1 locking");
                    System.out.println(ClassLayout.parseInstance(a).toPrintable());
                }
                try {
                    //thread1退出同步代码块，且没有死亡
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread2 = new Thread(){
            @Override
            public void run() {
                synchronized (a){
                    System.out.println("thread2 locking");
                    System.out.println(ClassLayout.parseInstance(a).toPrintable());
                }
            }
        };
        thread1.start();

        //让thread1执行完同步代码块中方法。
        Thread.sleep(3000);
        thread2.start();
    }
}
