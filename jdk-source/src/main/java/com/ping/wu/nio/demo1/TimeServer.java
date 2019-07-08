package com.ping.wu.nio.demo1;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * @author wuping
 * @date 2019-06-24
 */

public class TimeServer {
    public static void main(String[] args) throws Exception {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        Selector selector = Selector.open();
        SelectionKey key = ssc.register(selector, 0);
        key.interestOps(SelectionKey.OP_ACCEPT);
        ssc.bind(new InetSocketAddress(8080));
        while (true) {
            selector.select();
            Set<SelectionKey> sets = selector.selectedKeys();
            Iterator<SelectionKey> it = sets.iterator();
            while (it.hasNext()) {
                SelectionKey selectionKey = it.next();
                it.remove();
                handle(selectionKey);
            }
        }
    }

    public static void handle(SelectionKey key) {
        try {
            int readyOps = key.readyOps();
            if ((readyOps & SelectionKey.OP_ACCEPT) != 0) {
                SocketChannel ch = ((ServerSocketChannel) key.channel()).accept();
                ch.configureBlocking(false);
                ch.register(key.selector(), SelectionKey.OP_READ);
            }
            if ((readyOps & SelectionKey.OP_READ) != 0) {
                SocketChannel ch = ((SocketChannel) key.channel());
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                if (ch.read(byteBuffer) == -1) {
                    ch.close();
                    return;
                }
                if (byteBuffer.remaining() != 0) {
                    byteBuffer.flip();
                    byte[] content = new byte[byteBuffer.remaining()];
                    System.out.println("byteBuffer.remaining():" + byteBuffer.remaining() + "content.length:" + content.length);
                    byteBuffer.get(content);
                    System.out.println("recevied from client: " + new String(content));
                }
                key.interestOps(SelectionKey.OP_WRITE);
            }
            if ((readyOps & SelectionKey.OP_WRITE) != 0) {
                SocketChannel ch = (SocketChannel) key.channel();
                ByteBuffer byteBuffer = ByteBuffer.wrap(("现在的时间是:" + new Date()).getBytes());
                ch.write(byteBuffer);
                key.interestOps(SelectionKey.OP_READ);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
