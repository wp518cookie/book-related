//package com.ping.wu.juc;
//
//import com.ping.wu.util.UnsafeGenerator;
//import sun.misc.Unsafe;
//
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.locks.Condition;
//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReadWriteLock;
//
///**
// * @author wuping
// * @date 2019/2/21
// */
//
//public class ReentrantReadWriteLock
//        implements ReadWriteLock {
//    // -------------- field & constructor ------------------
//    private final ReentrantReadWriteLock.ReadLock readerLock;
//    private final ReentrantReadWriteLock.WriteLock writerLock;
//    final Sync sync;
//
//    public ReentrantReadWriteLock() {
//        this(false);
//    }
//
//    public ReentrantReadWriteLock(boolean fair) {
//        this.sync = fair ? new FairSync() : new NonfairSync();
//        this.readerLock = new ReadLock(this);
//        this.writerLock = new WriteLock(this);
//    }
//
//    abstract static class Sync extends AbstractQueuedSynchronizer {
//        static final int SHARED_SHIFT = 16;
//        static final int SHARED_UNIT = (1 << SHARED_SHIFT);
//        static final int MAX_COUNT = (1 << SHARED_SHIFT) - 1;
//        static final int EXCLUSIVE_MASK = (1 << SHARED_SHIFT) - 1;
//        private transient ThreadLocalHoldCounter readHolds;
//        private transient HoldCounter cachedHoldCounter;
//        private transient Thread firstReader = null;
//        private transient int firstReaderHoldCount;
//
//        static final class HoldCounter {
//            int count = 0;
//            final long tid = getThreadId(Thread.currentThread());
//        }
//
//        static final class ThreadLocalHoldCounter
//                extends ThreadLocal<HoldCounter> {
//            @Override
//            public HoldCounter initialValue() {
//                return new HoldCounter();
//            }
//        }
//
//        @Override
//        protected final boolean isHeldExclusively() {
//            return getExclusiveOwnerThread() == Thread.currentThread();
//        }
//
//        @Override
//        protected final boolean tryAcquire(int acquires) {
//            Thread current = Thread.currentThread();
//            int c = getState();
//            int w = exclusiveCount(c);
//            if (c != 0) {
//                // 说明有读锁被持有
//                if (w == 0 || current != getExclusiveOwnerThread()) {
//                    return false;
//                }
//                if (w + exclusiveCount(acquires) > MAX_COUNT) {
//                    throw new Error("Maximum lock count exceeded");
//                }
//                // 可重入
//                setState(c + acquires);
//                return true;
//            }
//            if (writerShouldBlock() || !(compareAndSetState(c, c + acquires))) {
//                return false;
//            }
//            setExclusiveOwnerThread(current);
//            return true;
//        }
//
//        @Override
//        protected final int tryAcquireShared(int unused) {
//            Thread current = Thread.currentThread();
//            int c = getState();
//            if (exclusiveCount(c) != 0 &&
//                    getExclusiveOwnerThread() != current) {
//                return -1;
//            }
//            int r = sharedCount(c);
//            if (!readerShouldBlock() &&
//                    r < MAX_COUNT &&
//                    compareAndSetState(c, c + SHARED_UNIT)) {
//                if (r == 0) {
//
//                }
//            }
//
//        }
//
//        static int exclusiveCount(int c) {
//            return c & EXCLUSIVE_MASK;
//        }
//
//        static int sharedCount(int c) {
//            return c >> SHARED_SHIFT;
//        }
//
//        abstract boolean writerShouldBlock();
//
//        abstract boolean readerShouldBlock();
//    }
//
//    static final class NonfairSync extends Sync {
//        @Override
//        final boolean writerShouldBlock() {
//            return false;
//        }
//
//        @Override
//        final boolean readerShouldBlock() {
//            return apparentlyFirstQueuedIsExclusive();
//        }
//    }
//
//    static final class FairSync extends Sync {
//        @Override
//        final boolean writerShouldBlock() {
//            return hasQueuedPredecessors();
//        }
//
//        @Override
//        final boolean readerShouldBlock() {
//            return hasQueuedPredecessors();
//        }
//    }
//
//    public static class ReadLock implements Lock {
//        private final Sync sync;
//
//        @Override
//        public void lock() {
//            sync.acquireShared(1);
//        }
//
//        protected ReadLock(ReentrantReadWriteLock lock) {
//            this.sync = lock.sync;
//        }
//
//        @Override
//        public void lockInterruptibly() throws InterruptedException {
//            sync.acquireSharedInterruptibly(1);
//        }
//
//        // 共享模式不支持condition
//        @Override
//        public Condition newCondition() {
//            throw new UnsupportedOperationException();
//        }
//
//        @Override
//        public void unlock() {
//            sync.releaseShared(1);
//        }
//
//        @Override
//        public boolean tryLock() {
//            return sync.tryReadLock();
//        }
//
//        @Override
//        public boolean tryLock(long timeout, TimeUnit unit)
//                throws InterruptedException {
//            return sync.tryAcquireSharedNanos(1, unit.toNanos(timeout));
//        }
//
//        @Override
//        public String toString() {
//            int r = sync.getReadLockCount();
//            return super.toString() +
//                    "[Read locks = " + r + "]";
//        }
//    }
//
//    public static class WriteLock implements Lock {
//        private final Sync sync;
//
//        @Override
//        public void lock() {
//            sync.acquire(1);
//        }
//
//        @Override
//        public void unlock() {
//            sync.release(1);
//        }
//
//        @Override
//        public void lockInterruptibly() throws InterruptedException {
//            sync.acquireInterruptibly(1);
//        }
//
//        protected WriteLock(ReentrantReadWriteLock lock) {
//            this.sync = lock.sync;
//        }
//
//        @Override
//        public boolean tryLock() {
//            return sync.tryWriteLock();
//        }
//
//        @Override
//        public boolean tryLock(long timeout, TimeUnit unit)
//                throws InterruptedException {
//            return sync.tryAcquireNanos(1, unit.toNanos(timeout));
//        }
//
//        @Override
//        public Condition newCondition() {
//            return sync.newCondition();
//        }
//
//        @Override
//        public String toString() {
//            Thread o = sync.getOwner();
//            return super.toString() + ((o == null) ? "[Unlocked]" :
//                    "[Locked by thread " + o.getName() + "]");
//        }
//
//        public boolean isHeldByCurrentThread() {
//            return sync.isHeldExclusively();
//        }
//
//        public int getHoldCount() {
//            return sync.getWriteHoldCount();
//        }
//    }
//
//    public ReentrantReadWriteLock.ReadLock ReadLock() {
//        return readerLock;
//    }
//
//    public ReentrantReadWriteLock.WriteLock WriteLock() {
//        return writerLock;
//    }
//
//    static final long getThreadId(Thread thread) {
//        return UNSAFE.getLongVolatile(thread, TID_OFFSET);
//    }
//
//    private static final Unsafe UNSAFE;
//    private static final long TID_OFFSET;
//
//    static {
//        try {
//            UNSAFE = UnsafeGenerator.getUnsafe();
//            Class<?> tk = Thread.class;
//            TID_OFFSET = UNSAFE.objectFieldOffset(tk.getDeclaredField("tid"));
//        } catch (Exception e) {
//            throw new Error(e);
//        }
//    }
//}
