package com.ping.wu.nio.test;

import java.nio.channels.spi.SelectorProvider;

/**
 * @author wuping
 * @date 2019-11-12
 */

public class SelectProviderTest {
    public static void main(String[] args) {
        SelectorProvider selectorProvider = SelectorProvider.provider();
        System.out.println(123);
    }
}
