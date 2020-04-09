package com.ping.wu.decorator;

/**
 * @author wuping
 * @date 2019-09-25
 */

public abstract class Drink {
    public String getDescription() {
        return "unknown drink";
    }

    public abstract double cost();
}
