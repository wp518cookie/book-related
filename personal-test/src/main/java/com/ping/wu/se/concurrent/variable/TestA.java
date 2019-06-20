package com.ping.wu.se.concurrent.variable;

import java.util.concurrent.TimeUnit;

/**
 * @author wuping
 * @date 2019/4/10
 */

public class TestA {
    private String a;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public static void main(String[] args) throws Exception {
        TestA t = new TestA();
        t.setA("abc");
        TestA.TestB testB = t.new TestB();
        testB.setB(t.getA());
        new Thread(testB).start();
        TimeUnit.SECONDS.sleep(3);
        t.setA("cde");
    }

    public void test(String a) {

    }

    private class TestB implements Runnable {
        private String b;

        public void setB(String b) {
            this.b = b;
        }

        @Override
        public void run() {
            try {
                for (; ; ) {
                    System.out.println(b);
                    TimeUnit.SECONDS.sleep(1);
                }
            } catch (Exception e) {

            }
        }
    }
}
