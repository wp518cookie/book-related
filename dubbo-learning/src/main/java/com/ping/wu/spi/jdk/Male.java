package com.ping.wu.spi.jdk;

/**
 * @author wuping
 * @date 2019-05-29
 */

public class Male implements Person {
    static {
        System.out.println("male loaded");
    }

    public Male() {
        System.out.println("male initialized!");
    }

    @Override
    public void saySomething() {
        System.out.println("i 'm male");
    }
}
