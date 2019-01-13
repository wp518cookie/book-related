package com.ping.wu.se.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author wuping
 * @date 2019/1/3
 */

public class SpiMain {
    public static void main(String[] args) {
        ServiceLoader<SpiService> spiServices = ServiceLoader.load(SpiService.class);
        Iterator<SpiService> it = spiServices.iterator();
        while (it.hasNext()) {
            it.next().saySomething();
        }
    }
}
