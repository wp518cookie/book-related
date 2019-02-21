package com.ping.wu.juc;

/**
 * @author wuping
 * @date 2019/2/20
 * 不靠谱
 */

public class MyCyclicBarrier {
    private final int originalState;
    private Sync sync;
    private volatile boolean canContinue = false;

    public MyCyclicBarrier(int state) {
        this.sync = new Sync(0);
        originalState = state;
    }

    private class Sync extends AbstractQueuedSynchronizer {

        Sync(int state) {
            setState(state);
        }

        @Override
        public boolean tryReleaseShared(int state) {
            return compareAndSetState(state, state - 1);
        }

        @Override
        public int tryAcquireShared(int arg) {
            for (; ; ) {
                int state = getState();
                if (canContinue) {
                    if (releaseShared(state)) {
                        if (state == 1) {
                            canContinue = false;
                            return 0;
                        } else {
                            return state - 1;
                        }
                    }
                } else {
                    if (state == originalState - 1) {
                        if (compareAndSetState(originalState - 1, originalState)) {
                            canContinue = true;
                            releaseShared(1);
                            return originalState - 1;
                        }
                    } else {
                        if (compareAndSetState(state, state + 1)) {
                            return -1;
                        }
                    }
                }
            }
        }
    }

    public void await() {
        sync.acquireShared(1);
    }

    public void reset() {
        sync.setState(originalState);
    }
}
