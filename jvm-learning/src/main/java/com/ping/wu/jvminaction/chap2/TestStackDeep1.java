package com.ping.wu.jvminaction.chap2;

/**
 * @author wuping
 * @date 2020-03-20
 */

public class TestStackDeep1 {
    private static int count = 0;

    // 8290
    public static void recursion(long a, long b, long c) {
        long e = 1, f = 2, g = 3, h = 4, i = 5, k = 6, q = 7, x = 8, y = 9, z = 10;
        count++;
        recursion(a, b, c);
    }

    // 21380
    public static void recursion() {
        count++;
        recursion(0L, 0L, 0L);
    }

    public static void main(String[] args) {
        try {
            recursion();
        } catch (Throwable e) {
            System.out.println("deep of calling = " + count);
            e.printStackTrace();
        }
    }
}
