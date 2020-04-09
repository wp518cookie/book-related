package com.ping.wu.decorator;

/**
 * @author wuping
 * @date 2019-09-25
 */

public class Mocha extends CondimentDrink {
    @Override
    public String getDescription() {
        return "mocha coffee";
    }

    @Override
    public double cost() {
        return 1.0;
    }
}
