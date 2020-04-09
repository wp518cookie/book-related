package com.ping.wu.command;

/**
 * @author wuping
 * @date 2019-10-01
 */

public class Stock {
    private String name = "ABC";
    private int quantity = 10;

    public void buy() {
        System.out.println("Stock [ name: " + name + ", Quantity: " + quantity + " ] bought");
    }

    public void sell() {
        System.out.println("Stock [ name: " + name + ", Quantitiy: " + quantity + " ] sold");
    }
}
