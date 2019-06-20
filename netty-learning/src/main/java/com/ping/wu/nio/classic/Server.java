package com.ping.wu.nio.classic;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author wuping
 * @date 2019-06-10
 */

public class Server implements Runnable {
    private int port;

    private Server(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(port);
            while (!Thread.interrupted()) {
                new Thread(new Handler(ss.accept())).start();
            }
        } catch (IOException e) {

        }
    }

    static class Handler implements Runnable {
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                byte[] input = new byte[1024];
                socket.getInputStream().read(input);
                byte[] output = process(input);
                socket.getOutputStream().write(output);
            } catch (IOException e) {

            }
        }

        private byte[] process(byte[] input) {
            //业务逻辑处理
            byte[] result = new byte[1024];
            return result;
        }
    }
}
