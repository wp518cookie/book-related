package com.ping.wu.command;

/**
 * @author wuping
 * @date 2019-10-01
 */

public class CommandTest {
    public static void main(String[] args) {
        Stock stock = new Stock();
        BuyStockOrder buyStockOrder = new BuyStockOrder(stock);
        SellStockOrder sellStockOrder = new SellStockOrder(stock);
        Broker broker = new Broker();
        broker.takeOrder(buyStockOrder);
        broker.takeOrder(sellStockOrder);
        broker.placeOrder();
    }
}
