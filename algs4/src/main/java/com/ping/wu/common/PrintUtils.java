package com.ping.wu.common;

/**
 * @author wuping
 * @date 2020-04-10
 */

public class PrintUtils {
    public static <T> void print(Iterable<T> t) {
        for (T temp : t) {
            System.out.print(" " + temp);
        }
        System.out.println();
    }
}
