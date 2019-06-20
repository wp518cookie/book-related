package com.ping.wu.producerconsumer;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author wuping
 * @date 2019-06-10
 */

public class Consumer implements Runnable {
    private StoreHouse storeHouse;

    public Consumer(StoreHouse storeHouse) {
        this.storeHouse = storeHouse;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            try {
                storeHouse.getSomething();
                TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(100, 200));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
