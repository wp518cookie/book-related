package com.ping.wu.juc;

/**
 * @author wuping
 * @date 2019/2/20
 */

public class Semaphore {
    private Sync sync;

    public Semaphore(int permit) {
        this.sync = new Sync(permit);
    }

    private static class Sync extends AbstractQueuedSynchronizer {
        Sync(int state) {
            setState(state);
        }

        @Override
        public int tryAcquireShared(int arg) {
            for (; ; ) {
                int state = getState();
                if (state <= 0) {
                    return -1;
                }
                int nextc = state - 1;
                if (compareAndSetState(state, nextc)) {
                    return nextc;
                }
            }
        }

        @Override
        public boolean tryReleaseShared(int arg) {
            for (; ; ) {
                int state = getState();
                int nextc = state + arg;
                if (compareAndSetState(state, nextc)) {
                    return nextc > 0;
                }
            }
        }
    }

    public void acquire() throws InterruptedException {
        sync.acquireSharedInterruptibly(1);
    }

    public void release() {
        sync.releaseShared(1);
    }
}
