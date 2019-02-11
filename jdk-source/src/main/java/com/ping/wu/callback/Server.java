package com.ping.wu.callback;

import java.util.concurrent.TimeUnit;

/**
 * @author wuping
 * @date 2019/1/13
 */

public class Server {
    public void process(String msg, Callback callBack) {
        System.out.println("received message from client:" + msg);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {

        }
        System.out.println("server process complete!");
        callBack.onComplete("I have received message : " + msg);
    }
}
