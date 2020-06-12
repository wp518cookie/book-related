package com.ping.wu.juc;

import com.ping.wu.util.UnsafeGenerator;
import sun.misc.Unsafe;
import sun.security.provider.SHA;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * @author wuping
 * @date 2020-05-27
 */

public class ReentrantReadWriteLock implements ReadWriteLock, Serializable {
    // ----------------------- field start -----------------------
    private final ReentrantReadWriteLock.ReadLock readerLock;
    private final ReentrantReadWriteLock.WriteLock writerLock;
    final Sync sync;

    public ReentrantReadWriteLock() {
        this(false);
    }

    // ----------------------- field end   -----------------------

    // ----------------------- method start -----------------------
    public ReentrantReadWriteLock(boolean fair) {
        sync = fair ? new FairSync() : new NonfairSync();
        readerLock = new ReadLock(this);
        writerLock = new WriteLock(this);
    }

    @Override
    public ReentrantReadWriteLock.ReadLock readLock() {
        return readerLock;
    }

    @Override
    public ReentrantReadWriteLock.WriteLock writeLock() {
        return writerLock;
    }
    // ----------------------- method end -----------------------

    // ----------------------- class start -----------------------

    static final class NonfairSync extends Sync {
        @Override
        final boolean writerShouldBlock() {
            return false;
        }

        @Override
        final boolean readerShouldBlock() {
            return apparentlyFirstQueuedIsExclusive();
        }
    }

    static final class FairSync extends Sync {

        @Override
        final boolean writerShouldBlock() {
            return hasQueuedPredecessors();
        }

        @Override
        final boolean readerShouldBlock() {
            return hasQueuedPredecessors();
        }
    }

    public static class WriteLock implements Lock, Serializable {
        private final Sync sync;

        protected WriteLock(ReentrantReadWriteLock lock) {
            sync = lock.sync;
        }

        public int getHoldCount() {
            return sync.getWriteHoldCount();
        }

        public boolean isHeldByCurrentThread() {
            return sync.isHeldExclusively();
        }

        @Override
        public void lock() {
            sync.acquire(1);
        }

        @Override
        public void lockInterruptibly() throws InterruptedException {
            sync.acquireInterruptibly(1);
        }

        @Override
        public Condition newCondition() {
            return sync.newCondition();
        }

        @Override
        public String toString() {
            Thread o = sync.getOwner();
            return super.toString() + ((o == null) ?
                    "[Unlocked]" :
                    "[Locked by thread " + o.getName() + "]");
        }

        @Override
        public boolean tryLock() {
            return sync.tryWriteLock();
        }

        @Override
        public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
            return sync.tryAcquireNanos(1, unit.toNanos(timeout));
        }

        @Override
        public void unlock() {
            sync.release(1);
        }
    }

    public static class ReadLock implements Lock, Serializable {
        private final Sync sync;

        protected ReadLock(ReentrantReadWriteLock lock) {
            sync = lock.sync;
        }

        @Override
        public void lock() {
            sync.acquireShared(1);
        }

        @Override
        public void lockInterruptibly() throws InterruptedException {
            sync.acquireSharedInterruptibly(1);
        }

        @Override
        public boolean tryLock() {
            return sync.tryReadLock();
        }

        @Override
        public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
            return sync.tryAcquireSharedNanos(1, unit.toNanos(timeout));
        }

        @Override
        public void unlock() {
            sync.releaseShared(1);
        }

        @Override
        public Condition newCondition() {
            throw new UnsupportedOperationException();
        }

        @Override
        public String toString() {
            int r = sync.getReadLockCount();
            return super.toString() +
                    "[Read locks = " + r + "]";
        }
    }

    abstract static class Sync extends AbstractQueuedSynchronizer {
        static final int SHARED_SHIFT = 16;
        static final int SHARED_UNIT = (1 << SHARED_SHIFT);
        static final int MAX_COUNT = (1 << SHARED_SHIFT) - 1;
        static final int EXCLUSIVE_MASK = (1 << SHARED_SHIFT) - 1;

        // 没看懂这个的作用
        private transient HoldCounter cachedHoldCounter;
        private transient Thread firstReader = null;
        private transient int firstReaderHoldCount;
        private transient ThreadLocalHoldCounter readHolds;

        Sync() {
            readHolds = new ThreadLocalHoldCounter();
        }

        static int sharedCount(int c) {
            return c >>> SHARED_SHIFT;
        }

        static int exclusiveCount(int c) {
            return c & EXCLUSIVE_MASK;
        }

        final int fullTryAcquireShared(Thread current) {
            HoldCounter rh = null;
            for (; ; ) {
                int c = getState();
                if (exclusiveCount(c) != 0) {
                    if (getExclusiveOwnerThread() != current) {
                        return -1;
                    }
                } else if (readerShouldBlock()) {
                    if (firstReader == current) {

                    } else {
                        if (rh == null) {
                            rh = cachedHoldCounter;
                            if (rh == null || rh.tid != getThreadId(current)) {
                                rh = readHolds.get();
                                if (rh.count == 0) {
                                    readHolds.remove();
                                }
                            }
                        }
                        if (rh.count == 0) {
                            return -1;
                        }
                    }
                }
                if (sharedCount(c) == MAX_COUNT) {
                    throw new Error("Maximum lock count exceeded");
                }
                if (compareAndSetState(c, c + SHARED_UNIT)) {
                    if (sharedCount(c) == 0) {
                        firstReader = current;
                        firstReaderHoldCount = 1;
                    } else if (firstReader == current) {
                        firstReaderHoldCount++;
                    } else {
                        if (rh == null) {
                            rh = cachedHoldCounter;
                        }
                        if (rh == null || rh.tid != getThreadId(current)) {
                            rh = readHolds.get();
                        } else if (rh.count == 0) {
                            readHolds.set(rh);
                        }
                        rh.count++;
                        cachedHoldCounter = rh;
                    }
                }
                return 1;
            }
        }

        final int getReadLockCount() {
            return sharedCount(getState());
        }

        final int getWriteHoldCount() {
            return isHeldExclusively() ? exclusiveCount(getState()) : 0;
        }

        final Thread getOwner() {
            return (exclusiveCount(getState()) == 0) ? null : getExclusiveOwnerThread();
        }

        @Override
        protected final boolean tryAcquire(int acquires) {
            Thread current = Thread.currentThread();
            int c = getState();
            int w = exclusiveCount(c);
            if (c != 0) {
                if (w == 0 || current != getExclusiveOwnerThread()) {
                    return false;
                }
                if (w + exclusiveCount(acquires) > MAX_COUNT) {
                    throw new Error("Maximum lock count exceeded");
                }
                setState(c + acquires);
                return true;
            }
            if (writerShouldBlock() || !compareAndSetState(c, c + acquires)) {
                return false;
            }
            setExclusiveOwnerThread(current);
            return true;
        }

        @Override
        protected final int tryAcquireShared(int unused) {
            Thread current = Thread.currentThread();
            int c = getState();
            if (exclusiveCount(c) != 0 && getExclusiveOwnerThread() != current) {
                return -1;
            }
            int r = sharedCount(c);
            if (!readerShouldBlock() && r < MAX_COUNT
                    && compareAndSetState(c, c + SHARED_UNIT)) {
                if (r == 0) {
                    firstReader = current;
                    firstReaderHoldCount = 1;
                } else if (firstReader == current) {
                    firstReaderHoldCount++;
                } else {
                    HoldCounter rh = cachedHoldCounter;
                    if (rh == null || rh.tid != getThreadId(current)) {
                        cachedHoldCounter = rh = readHolds.get();
                    } else if (rh.count == 0) {
                        readHolds.set(rh);
                    }
                    rh.count++;
                }
                return 1;
            }
            return fullTryAcquireShared(current);
        }

        @Override
        protected final boolean tryRelease(int releases) {
            if (!isHeldExclusively()) {
                throw new IllegalMonitorStateException();
            }
            int nextc = getState() - releases;
            boolean free = exclusiveCount(nextc) == 0;
            if (free) {
                setExclusiveOwnerThread(null);
            }
            setState(nextc);
            return free;
        }

        @Override
        protected final boolean isHeldExclusively() {
            return getExclusiveOwnerThread() == Thread.currentThread();
        }

        @Override
        protected final boolean tryReleaseShared(int unused) {
            Thread current = Thread.currentThread();
            if (firstReader == current) {
                if (firstReaderHoldCount == 1) {
                    firstReader = null;
                } else {
                    firstReaderHoldCount--;
                }
            } else {
                HoldCounter rh = cachedHoldCounter;
                if (rh == null || rh.tid != getThreadId(current)) {
                    rh = readHolds.get();
                }
                int count = rh.count;
                if (count <= 1) {
                    readHolds.remove();
                    if (count <= 0) {
                        throw unmatchedUnlockException();
                    }
                }
                --rh.count;
            }
            for (; ; ) {
                int c = getState();
                int nextc = c - SHARED_UNIT;
                if (compareAndSetState(c, nextc)) {
                    return nextc == 0;
                }
            }
        }

        private IllegalMonitorStateException unmatchedUnlockException() {
            return new IllegalMonitorStateException(
                    "attempt to unlock read lock, not locked by current thread");
        }

        abstract boolean readerShouldBlock();

        abstract boolean writerShouldBlock();

        final boolean tryReadLock() {
            Thread current = Thread.currentThread();
            for (; ; ) {
                int c = getState();
                if (exclusiveCount(c) != 0 && getExclusiveOwnerThread() != current) {
                    return false;
                }
                int r = sharedCount(c);
                if (r == MAX_COUNT) {
                    throw new Error("Maximum lock count exceeded");
                }
                if (compareAndSetState(c, c + SHARED_UNIT)) {
                    if (r == 0) {
                        firstReader = current;
                        firstReaderHoldCount = 1;
                    } else if (firstReader == current) {
                        firstReaderHoldCount++;
                    } else {
                        HoldCounter rh = cachedHoldCounter;
                        if (rh == null || rh.tid != getThreadId(current)) {
                            cachedHoldCounter = rh = readHolds.get();
                        } else if (rh.count == 0) {
                            readHolds.set(rh);
                        }
                        rh.count++;
                    }
                    return true;
                }
            }
        }

        final boolean tryWriteLock() {
            Thread current = Thread.currentThread();
            int c = getState();
            if (c != 0) {
                int w = exclusiveCount(c);
                if (w == 0 || current != getExclusiveOwnerThread()) {
                    return false;
                }
                if (w == MAX_COUNT) {
                    throw new Error("Maximum lock count exceeded");
                }
            }
            if (!compareAndSetState(c, c + 1)) {
                return false;
            }
            setExclusiveOwnerThread(current);
            return true;
        }

        final ConditionObject newCondition() {
            return new ConditionObject();
        }

        static final class ThreadLocalHoldCounter extends ThreadLocal<HoldCounter> {
            @Override
            public HoldCounter initialValue() {
                return new HoldCounter();
            }
        }

        static final class HoldCounter {
            int count = 0;
            final long tid = getThreadId(Thread.currentThread());
        }
    }

    // ----------------------- class end   -----------------------

    // ----------------------- static method start -----------------------

    static final long getThreadId(Thread thread) {
        return UNSAFE.getLongVolatile(thread, TID_OFFSET);
    }

    // ----------------------- static method end   -----------------------

    private static final Unsafe UNSAFE;
    private static final long TID_OFFSET;

    static {
        try {
            UNSAFE = UnsafeGenerator.getUnsafe();
            Class tk = Thread.class;
            TID_OFFSET = UNSAFE.objectFieldOffset(tk.getDeclaredField("tid"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }
}
