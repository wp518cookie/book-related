package com.ping.wu.jvm.invokedynamic;

/**
 * @author wuping
 * @date 2019-04-15
 *  javap -p -v -c LambdaTest
 */

public class LambdaTest {
    public static void main(String[] args) {
        Func add = (x, y) -> x + y;
        System.out.println(add.exec(1, 2));
    }
}

@FunctionalInterface
interface Func {
    int exec(int x, int y);
}