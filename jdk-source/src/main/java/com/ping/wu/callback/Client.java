package com.ping.wu.callback;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wuping
 * @date 2019/1/13
 */

public class Client {
    private Server server;
    private static ExecutorService exec = Executors.newFixedThreadPool(1);

    public Client(Server server) {
        this.server = server;
    }

    public static void main(String[] args) {
        Client client = new Client(new Server());
        exec.submit(() ->
                client.sendMsg("hello server!")
        );
        System.out.println("client send message complete!");
    }

    public void sendMsg(String msg) {
        server.process(msg, (result) ->
                System.out.println("response from server: " + result));
    }
}
