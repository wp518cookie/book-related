package com.ping.wu.juc;

import java.io.Serializable;

/**
 * @author wuping
 * @date 2019/2/14
 */

public abstract class AbstractOwnableSynchronizer
        implements Serializable {
    protected AbstractOwnableSynchronizer() {

    }

    private transient Thread exclusiveOwnerThread;

    protected final void setExclusiveOwnerThread(Thread thread) {
        exclusiveOwnerThread = thread;
    }

    protected final Thread getExclusiveOwnerThread() {
        return exclusiveOwnerThread;
    }
}
