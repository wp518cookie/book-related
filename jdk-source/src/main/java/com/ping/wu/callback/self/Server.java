package com.ping.wu.callback.self;

import java.util.concurrent.TimeUnit;

/**
 * @author wuping
 * @date 2020-08-31
 */

public class Server {
    public void process(String msg, Callback callback) {
        try {
            System.out.println("server开始处理来自客户端发来的msg:" + msg);
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {

        }
        callback.onComplete("来自客户端发来的msg" + msg + "已处理完毕");
    }
}
