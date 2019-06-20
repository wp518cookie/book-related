package com.ping.wu.jvm.invokedynamic;

/**
 * @author wuping
 * @date 2019-04-15
 */

public class Lambda {
    public static void PrintString(String s, Print<String> print) {
        print.print(s);
    }

    public static void main(String[] args) {
        PrintString("test", (x) -> System.out.println(x));
    }
}
