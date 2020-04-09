package com.ping.wu.decorator;

/**
 * @author wuping
 * @date 2019-09-25
 */

public class DecoratorTest {
    public static void main(String[] args) {
        CondimentDrink mocha = new Mocha();
        CondimentDrink milkMocha = new Milk(mocha);
        CondimentDrink iceMilkMocha = new Ice(milkMocha);
        System.out.println(iceMilkMocha.getDescription());
        System.out.println(iceMilkMocha.cost());
    }
}
