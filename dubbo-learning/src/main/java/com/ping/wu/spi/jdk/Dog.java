package com.ping.wu.spi.jdk;

/**
 * @author wuping
 * @date 2019-05-28
 */

public class Dog implements IShout {
    public Dog() {

    }

    @Override
    public void shout() {
        System.out.println("wang wang");
    }
}
