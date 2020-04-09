package com.ping.wu.decorator;

/**
 * @author wuping
 * @date 2019-09-25
 */

public class Milk extends CondimentDrink {
    private CondimentDrink drink;

    public Milk(CondimentDrink drink) {
        this.drink = drink;
    }

    @Override
    public String getDescription() {
        return drink.getDescription() + ", milk";
    }

    @Override
    public double cost() {
        return 0.5 + drink.cost();
    }
}
