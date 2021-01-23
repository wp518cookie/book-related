package com.ping.wu.blog.meituan.reward.state;

/**
 * @author wuping
 * @date 2021-01-12
 */

public class ConcreteStateA extends State {

    @Override
    public void handle1() {
        System.out.println("ConcreteStateA handle1");
    }

    @Override
    public void handle2() {
        System.out.println("ConcreteStateA handle2");
    }
}
