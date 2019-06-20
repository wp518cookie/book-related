package com.ping.wu.nio.multireactor;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wuping
 * @date 2019-06-11
 */

public class SubReactor implements Runnable {
    final Selector mySelector;

    int workCount = Runtime.getRuntime().availableProcessors();
    ExecutorService executorService = Executors.newFixedThreadPool(workCount);

    public SubReactor() throws Exception {
        this.mySelector = SelectorProvider.provider().openSelector();
    }

    public void registerChannel(SocketChannel sc) throws Exception {
        sc.register(mySelector, SelectionKey.OP_READ | SelectionKey.OP_CONNECT);
    }

    @Override
    public void run() {
        while (true) {
            try {
                mySelector.select();
                Set<SelectionKey> keys = mySelector.selectedKeys();
                Iterator<SelectionKey> iterator = keys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    if (key.isReadable()) {
                        read();
                    } else if (key.isWritable()) {
                        write();
                    }
                }
            } catch (Exception e) {

            }
        }
    }

    private void read() {

    }

    private void write() {

    }

    public void process() {

    }
}
