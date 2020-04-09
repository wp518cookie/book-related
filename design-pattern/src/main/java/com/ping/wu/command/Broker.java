package com.ping.wu.command;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wuping
 * @date 2019-10-01
 */

public class Broker {
    List<Order> orderList = new ArrayList();

    public void takeOrder(Order order) {
        orderList.add(order);
    }

    public void placeOrder() {
        for (Order order : orderList) {
            order.execute();
        }
        orderList.clear();
    }
}
