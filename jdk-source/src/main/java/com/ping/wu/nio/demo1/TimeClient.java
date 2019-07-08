package com.ping.wu.nio.demo1;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author wuping
 * @date 2019-06-24
 */

public class TimeClient {
    public static void main(String[] args) throws Exception {
        SocketChannel sc = SocketChannel.open();
        sc.configureBlocking(false);
        Selector selector = Selector.open();
        SelectionKey key = sc.register(selector, 0);
        if (!sc.connect(new InetSocketAddress("127.0.0.1", 8080))) {
            key.interestOps(SelectionKey.OP_CONNECT);
        } else {
            key.interestOps(SelectionKey.OP_READ);
        }
        while (true) {
            selector.select();
            Set<SelectionKey> sets = selector.selectedKeys();
            Iterator<SelectionKey> it = sets.iterator();
            while (it.hasNext()) {
                SelectionKey meta = it.next();
                it.remove();
                handle(meta);
            }
        }
    }

    public static void handle(SelectionKey key) {
        try {
            SocketChannel ch = (SocketChannel) key.channel();
            if ((key.readyOps() & SelectionKey.OP_CONNECT) != 0) {
                ch.finishConnect();
                ByteBuffer byteBuffer = ByteBuffer.wrap("请问现在的时间是?".getBytes());
                ch.write(byteBuffer);
                key.interestOps(SelectionKey.OP_READ);
            }
            if ((key.readyOps() & SelectionKey.OP_READ) != 0) {
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                ch.read(byteBuffer);
                byteBuffer.flip();
                if (byteBuffer.remaining() != 0) {
                    byte[] content = new byte[byteBuffer.remaining()];
                    byteBuffer.get(content);
                    System.out.println("收到服务端的返回：" + new String(content));
                }
                ch.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
