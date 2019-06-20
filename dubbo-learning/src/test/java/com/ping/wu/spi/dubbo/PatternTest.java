package com.ping.wu.spi.dubbo;

import java.util.regex.Pattern;

/**
 * @author wuping
 * @date 2019-05-29
 */

public class PatternTest {
    private static final Pattern NAME_SEPARATOR = Pattern.compile("\\s*[,]+\\s*");

    public static void main(String[] args) {
        String s = "\ntest=1234123\n";
        String[] arr = NAME_SEPARATOR.split(s);
        System.out.println(arr.length);
    }
}
