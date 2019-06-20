package com.ping.wu.spi.jdk;

/**
 * @author wuping
 * @date 2019-05-28
 */

public class Cat implements IShout {
    public Cat() {

    }

    @Override
    public void shout() {
        System.out.println("miao miao");
    }
}
