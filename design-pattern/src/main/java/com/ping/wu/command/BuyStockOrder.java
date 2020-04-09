package com.ping.wu.command;

/**
 * @author wuping
 * @date 2019-10-01
 */

public class BuyStockOrder implements Order {
    private Stock stock;

    public BuyStockOrder(Stock stock) {
        this.stock = stock;
    }

    @Override
    public void execute() {
        stock.buy();
    }
}
