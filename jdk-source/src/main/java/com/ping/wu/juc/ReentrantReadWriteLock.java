package com.ping.wu.juc;

import com.ping.wu.util.UnsafeGenerator;
import sun.misc.Unsafe;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * @author wuping
 * @date 2019/2/21
 */

public class ReentrantReadWriteLock
        implements ReadWriteLock {
    // -------------- field & constructor ------------------
    private final ReentrantReadWriteLock.ReadLock readerLock;
    private final ReentrantReadWriteLock.WriteLock writerLock;
    final Sync sync;

    public ReentrantReadWriteLock() {
        this(false);
    }

    public ReentrantReadWriteLock(boolean fair) {
        this.sync = fair ? new FairSync() : new NonfairSync();
        this.readerLock = new ReadLock(this);
        this.writerLock = new WriteLock(this);
    }

    abstract static class Sync extends AbstractQueuedSynchronizer {
        static final int SHARED_SHIFT = 16;
        static final int SHARED_UNIT = (1 << SHARED_SHIFT);
        static final int MAX_COUNT = (1 << SHARED_SHIFT) - 1;
        static final int EXCLUSIVE_MASK = (1 << SHARED_SHIFT) - 1;
        private transient ThreadLocalHoldCounter readHolds;
        private transient HoldCounter cachedHoldCounter;
        private transient Thread firstReader = null;
        private transient int firstReaderHoldCount;

        static final class HoldCounter {
            int count = 0;
            final long tid = getThreadId(Thread.currentThread());
        }

        static final class ThreadLocalHoldCounter
                extends ThreadLocal<HoldCounter> {
            @Override
            public HoldCounter initialValue() {
                return new HoldCounter();
            }
        }

        @Override
        protected final boolean isHeldExclusively() {
            return getExclusiveOwnerThread() == Thread.currentThread();
        }

        @Override
        protected final boolean tryAcquire(int acquires) {
            Thread current = Thread.currentThread();
            int c = getState();
            int w = exclusiveCount(c);
            if (c != 0) {
                // 说明有读锁被持有
                if (w == 0 || current != getExclusiveOwnerThread()) {
                    return false;
                }
                if (w + exclusiveCount(acquires) > MAX_COUNT) {
                    throw new Error("Maximum lock count exceeded");
                }
                // 可重入
                setState(c + acquires);
                return true;
            }
            if (writerShouldBlock() || !(compareAndSetState(c, c + acquires))) {
                return false;
            }
            setExclusiveOwnerThread(current);
            return true;
        }

        // todo 理解
        @Override
        protected final int tryAcquireShared(int unused) {
            Thread current = Thread.currentThread();
            int c = getState();
            if (exclusiveCount(c) != 0 &&
                    getExclusiveOwnerThread() != current) {
                return -1;
            }
            int r = sharedCount(c);
            if (!readerShouldBlock() &&
                    r < MAX_COUNT &&
                    compareAndSetState(c, c + SHARED_UNIT)) {
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

        //todo doug lea这个糟老头子在干啥？
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
                            if (rh == null || rh.tid != current.getId()) {
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
                if(sharedCount(c) == MAX_COUNT) {
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
                        if (rh == null || rh.tid != current.getId()) {
                            rh = readHolds.get();
                        } else if (rh.count == 0) {
                            readHolds.set(rh);
                        }
                        rh.count++;
                        cachedHoldCounter = rh;
                    }
                    return 1;
                }
            }
        }

        static int exclusiveCount(int c) {
            return c & EXCLUSIVE_MASK;
        }

        static int sharedCount(int c) {
            return c >> SHARED_SHIFT;
        }

        abstract boolean writerShouldBlock();

        abstract boolean readerShouldBlock();

        final Thread getOwner() {
            return (exclusiveCount(getState()) == 0 ? null : getExclusiveOwnerThread());
        }

        final int getReadLockCount() {
            return sharedCount(getState());
        }

        final boolean isWriteLocked() {
            return exclusiveCount(getState()) != 0;
        }

        final int getWriteHoldCount() {
            return isHeldExclusively() ? exclusiveCount(getState()) : 0;
        }

        final int getReadHoldCount() {
            if (getReadLockCount() == 0) {
                return 0;
            }
            Thread current = Thread.currentThread();
            if (firstReader == current) {
                return firstReaderHoldCount;
            }
            HoldCounter ch = cachedHoldCounter;
            if (ch != null && ch.tid == getThreadId(current)) {
                return ch.count;
            }
            int count = readHolds.get().count;
            if (count ==0) {
                readHolds.remove();
            }
            return count;
        }

        final ConditionObject newCondition() {
            return new ConditionObject();
        }

        final boolean tryReadLock() {
            Thread current = Thread.currentThread();
            for (; ; ) {
                int c = getState();
                if (exclusiveCount(c) != 0 &&
                        getExclusiveOwnerThread() != current) {
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
                            rh = readHolds.get();
                            cachedHoldCounter = rh;
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
    }

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

    public static class ReadLock implements Lock {
        private final Sync sync;

        @Override
        public void lock() {
            sync.acquireShared(1);
        }

        protected ReadLock(ReentrantReadWriteLock lock) {
            this.sync = lock.sync;
        }

        @Override
        public void lockInterruptibly() throws InterruptedException {
            sync.acquireSharedInterruptibly(1);
        }

        // 共享模式不支持condition
        @Override
        public Condition newCondition() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void unlock() {
            sync.releaseShared(1);
        }

        @Override
        public boolean tryLock() {
            return sync.tryReadLock();
        }

        @Override
        public boolean tryLock(long timeout, TimeUnit unit)
                throws InterruptedException {
            return sync.tryAcquireSharedNanos(1, unit.toNanos(timeout));
        }

        @Override
        public String toString() {
            int r = sync.getReadLockCount();
            return super.toString() +
                    "[Read locks = " + r + "]";
        }
    }

    public static class WriteLock implements Lock {
        private final Sync sync;

        @Override
        public void lock() {
            sync.acquire(1);
        }

        @Override
        public void unlock() {
            sync.release(1);
        }

        @Override
        public void lockInterruptibly() throws InterruptedException {
            sync.acquireInterruptibly(1);
        }

        protected WriteLock(ReentrantReadWriteLock lock) {
            this.sync = lock.sync;
        }

        @Override
        public boolean tryLock() {
            return sync.tryWriteLock();
        }

        @Override
        public boolean tryLock(long timeout, TimeUnit unit)
                throws InterruptedException {
            return sync.tryAcquireNanos(1, unit.toNanos(timeout));
        }

        @Override
        public Condition newCondition() {
            return sync.newCondition();
        }

        @Override
        public String toString() {
            Thread o = sync.getOwner();
            return super.toString() + ((o == null) ? "[Unlocked]" :
                    "[Locked by thread " + o.getName() + "]");
        }

        public boolean isHeldByCurrentThread() {
            return sync.isHeldExclusively();
        }

        public int getHoldCount() {
            return sync.getWriteHoldCount();
        }
    }

    @Override
    public ReentrantReadWriteLock.ReadLock readLock() {
        return readerLock;
    }

    @Override
    public ReentrantReadWriteLock.WriteLock writeLock() {
        return writerLock;
    }

    static final long getThreadId(Thread thread) {
        return UNSAFE.getLongVolatile(thread, TID_OFFSET);
    }

    private static final Unsafe UNSAFE;
    private static final long TID_OFFSET;

    static {
        try {
            UNSAFE = UnsafeGenerator.getUnsafe();
            Class<?> tk = Thread.class;
            TID_OFFSET = UNSAFE.objectFieldOffset(tk.getDeclaredField("tid"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }
}
