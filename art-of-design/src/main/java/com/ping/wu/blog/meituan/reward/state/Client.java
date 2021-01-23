package com.ping.wu.blog.meituan.reward.state;

/**
 * @author wuping
 * @date 2021-01-13
 */

public class Client {
    public static void main(String[] args) {
        Context context = new Context();
        context.setCurrentState(new ConcreteStateA());
        context.handle1();
        context.handle2();
    }
}
