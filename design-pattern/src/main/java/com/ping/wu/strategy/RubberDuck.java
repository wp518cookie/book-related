package com.ping.wu.strategy;

/**
 * @author wuping
 * @date 2019-09-25
 */

public class RubberDuck extends Duck {
    public RubberDuck() {
        flyBehavior = new FlyNoWay();
        quackBehavior = new QuackNoWay();
    }

    @Override
    public void display() {
        System.out.println("i'm rubber duck");
    }

    public static void main(String[] args) {
        RubberDuck rubberDuck = new RubberDuck();
        rubberDuck.setFlyBehavior(new FlyWithWings());
        rubberDuck.performFly();
        rubberDuck.performQuack();
    }
}
