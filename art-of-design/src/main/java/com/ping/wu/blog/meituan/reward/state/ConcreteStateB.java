package com.ping.wu.blog.meituan.reward.state;

/**
 * @author wuping
 * @date 2021-01-12
 */

public class ConcreteStateB extends State {

    @Override
    public void handle1() {
        System.out.println("ConcreteStateB handle1");
    }

    @Override
    public void handle2() {
        System.out.println("ConcreteStateB handle2");
    }
}
