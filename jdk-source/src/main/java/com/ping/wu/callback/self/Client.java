package com.ping.wu.callback.self;

/**
 * @author wuping
 * @date 2020-08-31
 */

public class Client {
    private Server server;

    public Client(Server server) {
        this.server = server;
    }

    public static void main(String[] args) {
        Server server = new Server();
        Client client = new Client(server);
        client.sendMessage("hello world");
    }

    public void sendMessage(String msg) {
        server.process(msg, (result) -> {
            System.out.println("收到来自server端发来的结果:" + result);
        });
    }
}
