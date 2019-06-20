package com.ping.wu.jvm.gc;

/**
 * @author wuping
 * @date 2019/4/10
 * 为了加速对象的分配，因为堆是共享的，分配对象时不同的线程形成竞争，每次分配需要同步。预先针对线程分配一块空间，该空间位于eden，成为TLAB : thread local allocation buffer
 * -XX:+UseTLAB -Xcomp -XX:-BackgroundCompilation -XX:-DoEscapeAnalysis -server
 */

public class TLABTest {
    public static void alloc() {
        byte[] b = new byte[2];
        b[0] = 1;
    }

    public static void main(String[] args) {
        long b = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            alloc();
        }
        long e = System.currentTimeMillis();
        System.out.println(e - b);
    }
}
