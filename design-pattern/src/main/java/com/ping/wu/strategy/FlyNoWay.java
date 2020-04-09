package com.ping.wu.strategy;

/**
 * @author wuping
 * @date 2019-09-25
 */

public class FlyNoWay implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("i can't fly");
    }
}
