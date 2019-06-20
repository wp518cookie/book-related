package com.ping.wu.test.bit;

import org.junit.Test;

/**
 * @author wuping
 * @date 2019-05-27
 */

public class ExpansionTest {
    @Test
    public void testExpansion() {
        int a = -1;
        System.out.println(Integer.toBinaryString(a));
        long b = (long) a;
        System.out.println(Long.toBinaryString(b));
    }
}
