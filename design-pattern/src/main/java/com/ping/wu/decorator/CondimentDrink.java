package com.ping.wu.decorator;

/**
 * @author wuping
 * @date 2019-09-25
 */

public abstract class CondimentDrink extends Drink {
    Drink drink;

    @Override
    public abstract String getDescription();
}
