package com.ping.wu.juc;

/**
 * @author wuping
 * @date 2019/2/11
 */

public interface RejectedExecutionHandler {
    void rejectedExecution(Runnable r, ThreadPoolExecutor executor);
}
