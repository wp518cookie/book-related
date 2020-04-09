package com.ping.wu.command;

/**
 * @author wuping
 * @date 2019-10-01
 */

public class SellStockOrder implements Order {
    private Stock stock;

    public SellStockOrder(Stock stock) {
        this.stock = stock;
    }

    @Override
    public void execute() {
        stock.sell();
    }
}
