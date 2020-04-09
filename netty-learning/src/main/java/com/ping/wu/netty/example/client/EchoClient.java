package com.ping.wu.netty.example.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author wuping
 * @date 2019-11-19
 */

public class EchoClient {
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                EventLoopGroup g = new NioEventLoopGroup();
                EchoClientHandler handler = new EchoClientHandler();
                Bootstrap b = new Bootstrap();
                try {
                    b.group(g)
                            .channel(NioSocketChannel.class)
                            .remoteAddress(new InetSocketAddress("127.0.0.1", 8989))
                            .handler(new ChannelInitializer<Channel>() {

                                @Override
                                protected void initChannel(Channel channel) throws Exception {
                                    channel.pipeline().addLast(handler);
                                }
                            });
                    ChannelFuture f = b.connect().sync();
                    f.channel().closeFuture().sync();
                } catch (Exception e) {

                } finally {
                    try {
                        g.shutdownGracefully().sync();
                    } catch (Exception e) {

                    }
                }
            }).start();
        }
    }
}
