package com.ping.wu.decorator;

/**
 * @author wuping
 * @date 2019-09-25
 */

public class Ice extends CondimentDrink {
    private CondimentDrink drink;

    public Ice(CondimentDrink drink) {
        this.drink = drink;
    }

    @Override
    public String getDescription() {
        return drink.getDescription();
    }

    @Override
    public double cost() {
        return 0.5 + drink.cost();
    }
}
