package com.ping.wu.nio.multireactor;

import java.net.ServerSocket;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author wuping
 * @date 2019-06-11
 */

public class MultiWorkThreadAcceptor implements Runnable {
    int workCount = Runtime.getRuntime().availableProcessors();
    SubReactor[] workThreadHandlers = new SubReactor[workCount];
    volatile int nextHandler = 0;
    private ServerSocketChannel ssc;

    public MultiWorkThreadAcceptor(ServerSocketChannel ssc) {
        this.init();
        this.ssc = ssc;
    }

    public void init() {
        nextHandler = 0;
        try {
            for (int i = 0; i < workCount; i++) {
                workThreadHandlers[i] = new SubReactor();
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void run() {
        try {
            SocketChannel sc = ssc.accept();
            if (sc != null) {
                synchronized (sc) {
                    SubReactor work = workThreadHandlers[nextHandler++];
                    work.registerChannel(sc);
                    if (nextHandler >= workThreadHandlers.length) {
                        nextHandler = 0;
                    }
                }
            }
        } catch (Exception e) {

        }
    }
}
