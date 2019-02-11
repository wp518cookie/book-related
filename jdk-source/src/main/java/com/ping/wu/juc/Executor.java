package com.ping.wu.juc;

/**
 * @author wuping
 * @date 2019/2/2
 */

public interface Executor {
    void execute(Runnable command);
}
