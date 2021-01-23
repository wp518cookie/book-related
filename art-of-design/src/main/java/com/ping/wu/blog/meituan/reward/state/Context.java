package com.ping.wu.blog.meituan.reward.state;

/**
 * @author wuping
 * @date 2021-01-12
 */

public class Context {
    public final static ConcreteStateA concreteStateA = new ConcreteStateA();
    public final static ConcreteStateB concreteStateB = new ConcreteStateB();

    private State currentState;

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
        this.currentState.setContext(this);
    }

    public void handle1() {
        currentState.handle1();
    }

    public void handle2() {
        currentState.handle2();
    }
}
