package com.ping.wu.strategy;

/**
 * @author wuping
 * @date 2019-09-25
 */

public class QuackNoWay implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("i can't quack");
    }
}
