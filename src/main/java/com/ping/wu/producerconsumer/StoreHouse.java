package com.ping.wu.producerconsumer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wuping
 * @date 2019-06-10
 */

public class StoreHouse {
    public List<String> products = new ArrayList();
    private int i= 0;
    private int MAX = 5;

    public synchronized void putSomething() throws InterruptedException {
        while (products.size() == MAX) {
            wait();
        }
        products.add("货品" + i + "号");
        i++;
        notifyAll();
    }

    public synchronized void getSomething() throws InterruptedException {
        while (products.size() == 0) {
            wait();
        }
        String s = products.remove(0);
        System.out.println("消费" + s);
        notifyAll();
    }

    public static void main(String[] args) {
        StoreHouse storeHouse = new StoreHouse();
        for (int i = 0; i < 10; i++) {
            new Thread(new Producer(storeHouse)).start();
            new Thread(new Consumer(storeHouse)).start();
        }
    }
}
