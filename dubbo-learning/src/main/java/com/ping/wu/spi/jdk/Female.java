package com.ping.wu.spi.jdk;

/**
 * @author wuping
 * @date 2019-05-29
 */

public class Female implements Person {
    static {
        System.out.println("female loaded");
    }

    public Female() {
        System.out.println("female initialized!");
    }

    @Override
    public void saySomething() {
        System.out.println("I 'm female !");
    }
}
