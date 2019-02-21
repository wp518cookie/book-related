package com.ping.wu.juc;

import java.util.concurrent.TimeUnit;

/**
 * @author wuping
 * @date 2019/2/18
 */

public class CountDownLatch {
    private Sync sync;

    public CountDownLatch(int state) {
        sync = new Sync(state);
    }

    private static class Sync extends AbstractQueuedSynchronizer {
        public Sync(int state) {
            setState(state);
        }

        @Override
        protected int tryAcquireShared(int acquires) {
            return getState() == 0 ? 1 : -1;
        }

        @Override
        public boolean tryReleaseShared(int arg) {
            for (; ; ) {
                int c = getState();
                if (c == 0) {
                    return false;
                }
                int nextc = c -1;
                if (compareAndSetState(c, nextc)) {
                    return nextc == 0;
                }
            }
        }
    }

    public void countDown() {
        sync.releaseShared(1);
    }

    public void await() throws InterruptedException {
        sync.acquireSharedInterruptibly(1);
    }

    public void await(long timeout, TimeUnit unit) throws InterruptedException {
        sync.tryAcquireSharedNanos(1, unit.toNanos(timeout));
    }
}
