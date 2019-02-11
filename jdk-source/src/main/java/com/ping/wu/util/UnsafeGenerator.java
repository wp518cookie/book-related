package com.ping.wu.util;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author wuping
 * @date 2018/12/29
 */

public class UnsafeGenerator {
    private static volatile Unsafe unsafe = null;
    public static Unsafe getUnsafe() {
        if (unsafe == null) {
            synchronized (UnsafeGenerator.class) {
                if (unsafe == null) {
                    try {
                        Field field = Unsafe.class.getDeclaredField("theUnsafe");
                        field.setAccessible(true);
                        unsafe = (Unsafe) field.get(null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return unsafe;
    }
}
