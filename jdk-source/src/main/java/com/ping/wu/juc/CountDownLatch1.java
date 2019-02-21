package com.ping.wu.juc;

/**
 * @author wuping
 * @date 2019/2/20
 */

public class CountDownLatch1 {
    private Sync sync;

    private static class Sync extends AbstractQueuedSynchronizer {
        Sync(int state) {
            setState(state);
        }

        /**
         * 返回true，表示该对象现在已经被完全释放了，其他等待线程可以尝试获取
         * @param arg
         * @return
         */
        @Override
        public boolean tryRelease(int arg) {
            int state = getState();
            for (; ; ) {
                if (state == 0) {
                    return false;
                } else {
                    int nextc = state - 1;
                    if (compareAndSetState(state, nextc)) {
                        return nextc == 0;
                    }
                }
            }
        }

        @Override
        public boolean tryAcquire(int arg) {
            return getState() == 0;
        }
    }

    public CountDownLatch1(int state) {
        this.sync = new Sync(state);
    }

    public void countDown() {
        sync.release(1);
    }

    public void await() {
        sync.acquire(1);
    }
}
