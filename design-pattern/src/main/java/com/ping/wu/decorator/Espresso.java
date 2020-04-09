package com.ping.wu.decorator;

/**
 * @author wuping
 * @date 2019-09-25
 */

public class Espresso extends Drink {

    @Override
    public String getDescription() {
        return "espresso coffee";
    }

    @Override
    public double cost() {
        return 1.0;
    }
}
