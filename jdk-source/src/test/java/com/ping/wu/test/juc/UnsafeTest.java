package com.ping.wu.test.juc;

import com.ping.wu.util.UnsafeGenerator;
import org.junit.Test;
import sun.misc.Unsafe;

import java.util.concurrent.TimeUnit;

/**
 * @author wuping
 * @date 2019/1/31
 */

public class UnsafeTest {
    @Test
    public void test() throws Exception {
        Unsafe unsafe = UnsafeGenerator.getUnsafe();

        System.out.println("thread has unparked");
        TimeUnit.SECONDS.sleep(3);
        unsafe.park(false, 0);
        unsafe.unpark(Thread.currentThread());
        System.out.println("thread done");
    }
}
