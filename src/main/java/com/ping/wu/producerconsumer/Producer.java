package com.ping.wu.producerconsumer;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author wuping
 * @date 2019-06-10
 */

public class Producer implements Runnable {
    private StoreHouse storeHouse;

    public Producer(StoreHouse storeHouse) {
        this.storeHouse = storeHouse;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 1000; i++) {
                storeHouse.putSomething();
                TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(100, 200));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
