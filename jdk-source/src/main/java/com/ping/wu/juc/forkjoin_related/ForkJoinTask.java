//package com.ping.wu.juc.forkjoin_related;
//
//import com.ping.wu.util.UnsafeGenerator;
//import sun.misc.Unsafe;
//
//import java.io.Serializable;
//import java.lang.ref.ReferenceQueue;
//import java.lang.ref.WeakReference;
//import java.lang.reflect.Constructor;
//import java.util.concurrent.Callable;
//import java.util.concurrent.CancellationException;
//import java.util.concurrent.CountedCompleter;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.Future;
//import java.util.concurrent.RunnableFuture;
//import java.util.concurrent.TimeoutException;
//import java.util.concurrent.locks.ReentrantLock;
//
///**
// * @author wuping
// * @date 2020-03-31
// */
//
//public abstract class ForkJoinTask<V> implements Future<V>, Serializable {
//
//    // ------------------------------ field ------------------------------
//    volatile int status;
//    static final int DONE_MASK = 0xf0000000;
//    static final int NORMAL = 0xf0000000;
//    static final int CANCELLED = 0xc0000000;
//    static final int EXCEPTIONAL = 0x80000000;
//    static final int SIGNAL = 0x00010000;
//    static final int SMASK = 0x0000ffff;
//
//    private static final int EXCEPTION_MAP_CAPACITY = 32;
//
//    private static final ForkJoinTask.ExceptionNode[] exceptionTable;
//    private static final ReentrantLock exceptionTableLock;
//    private static final ReferenceQueue<Object> exceptionTableRefQueue;
//
//    private static final Unsafe U;
//    private static final long STATUS;
//
//    static {
//        exceptionTable = new ExceptionNode[EXCEPTION_MAP_CAPACITY];
//        exceptionTableLock = new ReentrantLock();
//        exceptionTableRefQueue = new ReferenceQueue<>();
//        try {
//            U = UnsafeGenerator.getUnsafe();
//            Class<?> k = ForkJoinTask.class;
//            STATUS = U.objectFieldOffset(k.getDeclaredField("status"));
//        } catch (Exception e) {
//            throw new Error(e);
//        }
//    }
//
//    // ------------------------------ field ------------------------------
//
//    // ------------------------------ static class ------------------------------
//
//    static final class ExceptionNode extends WeakReference<ForkJoinTask<?>> {
//        final Throwable ex;
//        ExceptionNode next;
//        final long thrower;
//        final int hashCode;
//
//        ExceptionNode(ForkJoinTask<?> task, Throwable ex, ExceptionNode next) {
//            super(task, exceptionTableRefQueue);
//            this.ex = ex;
//            this.next = next;
//            this.thrower = Thread.currentThread().getId();
//            this.hashCode = System.identityHashCode(task);
//        }
//    }
//
//    static final class AdaptedCallable<T> extends ForkJoinTask<T> implements RunnableFuture<T> {
//        final Callable<? extends T> callable;
//        T result;
//
//        AdaptedCallable(Callable<? extends T> callable) {
//            if (callable == null) {
//                throw new NullPointerException();
//            }
//            this.callable = callable;
//        }
//
//        public final T getRawResult() {
//            return result;
//        }
//
//        public final void setRawResult(T v) {
//            this.result = v;
//        }
//
//        public final boolean exec() {
//            try {
//                result = callable.call();
//                return true;
//            } catch (Error err) {
//                throw err;
//            } catch (RuntimeException rex) {
//                throw rex;
//            } catch (Exception ex) {
//                throw new RuntimeException(ex);
//            }
//        }
//
//        @Override
//        public final void run() {
//            invoke();
//        }
//    }
//
//    static final class AdaptedRunnable<T> extends ForkJoinTask<T> implements RunnableFuture<T> {
//        final Runnable runnable;
//        T result;
//
//        AdaptedRunnable(Runnable runnable, T result) {
//            if (runnable == null) {
//                throw new NullPointerException();
//            }
//            this.runnable = runnable;
//            this.result = result;
//        }
//
//        public final T getRawResult() {
//            return result;
//        }
//
//        public final void setRawResult(T v) {
//            result = v;
//        }
//
//        public final boolean exec() {
//            runnable.run();
//            return true;
//        }
//
//        @Override
//        public final void run() {
//            invoke();
//        }
//    }
//
//    static final class AdaptedRunnableAction extends ForkJoinTask<Void> implements RunnableFuture<Void> {
//        final Runnable runnable;
//
//        AdaptedRunnableAction(Runnable runnable) {
//            if (runnable == null) throw new NullPointerException();
//            this.runnable = runnable;
//        }
//
//        public final Void getRawResult() {
//            return null;
//        }
//
//        public final void setRawResult(Void v) {
//        }
//
//        public final boolean exec() {
//            runnable.run();
//            return true;
//        }
//
//        @Override
//        public final void run() {
//            invoke();
//        }
//    }
//
//    static final class RunnableExecuteAction extends ForkJoinTask<Void> {
//        final Runnable runnable;
//
//        RunnableExecuteAction(Runnable runnable) {
//            if (runnable == null) {
//                throw new NullPointerException();
//            }
//            this.runnable = runnable;
//        }
//
//        public final Void getRawResult() {
//            return null;
//        }
//
//        public final void setRawResult(Void v) {
//        }
//
//        public final boolean exec() {
//            runnable.run();
//            return true;
//        }
//
//        void internalPropagateException(Throwable ex) {
//            rethrow(ex);
//        }
//    }
//
//    // ------------------------------ static class ------------------------------
//
//    // ------------------------------ method ------------------------------
//
//    public static ForkJoinTask<?> adapt(Runnable runnable) {
//        return new AdaptedRunnableAction(runnable);
//    }
//
//    public static <T> ForkJoinTask<T> adapt(Runnable runnable, T result) {
//        return new AdaptedRunnable<T>(runnable, result);
//    }
//
//    public static <T> ForkJoinTask<T> adapt(Callable<? extends T> callable) {
//        return new AdaptedCallable<T>(callable);
//    }
//
//    @Override
//    public boolean cancel(boolean mayInterruptIfRunning) {
//        return (setCompletion(CANCELLED) & DONE_MASK) == CANCELLED;
//    }
//
//    static final void cancelIgnoringExceptions(ForkJoinTask<?> t) {
//        if (t != null && t.status >= 0) {
//            try {
//                t.cancel(false);
//            } catch (Throwable ignore) {
//
//            }
//        }
//    }
//
//    private void clearExceptionalCompletion() {
//        int h = System.identityHashCode(this);
//        final ReentrantLock lock = exceptionTableLock;
//        lock.lock();
//        try {
//            ExceptionNode[] t = exceptionTable;
//            int i = h & (t.length - 1);
//            ExceptionNode e = t[i];
//            ExceptionNode pred = null;
//            while (e != null) {
//                ExceptionNode next = e.next;
//                if (e.get() == this) {
//                    if (pred == null) {
//                        t[i] = next;
//                    } else {
//                        pred.next = next;
//                    }
//                    break;
//                }
//                pred = e;
//                e = next;
//            }
//            expungeStaleExceptions();
//            status = 0;
//        } finally {
//            lock.unlock();
//        }
//    }
//
//    public final boolean compareAndSetForkJoinTaskTag(short e, short tag) {
//        for (int s; ; ) {
//            if ((short) (s = status) != e) {
//                return false;
//            }
//            if (U.compareAndSwapInt(this, STATUS, s, (s & ~SMASK) | (tag & SMASK))) {
//                return true;
//            }
//        }
//    }
//
//    public void complete(V value) {
//        try {
//            setRawResult(value);
//        } catch (Throwable rex) {
//            setExceptionalCompletion(rex);
//            return;
//        }
//        setCompletion(NORMAL);
//    }
//
//    public void completeExceptionally(Throwable ex) {
//        setExceptionalCompletion((ex instanceof RuntimeException) ||
//                (ex instanceof Error) ? ex :
//                new RuntimeException(ex));
//    }
//
//    final int doExec() {
//        int s;
//        boolean completed;
//        if ((s = status) >= 0) {
//            try {
//                completed = exec();
//            } catch (Throwable rex) {
//                return setExceptionalCompletion(rex);
//            }
//            if (completed) {
//                s = setCompletion(NORMAL);
//            }
//        }
//        return s;
//    }
//
//    private int doInvoke() {
//        int s;
//        Thread t;
//        ForkJoinWorkerThread wt;
//        return (s = doExec()) < 0 ? s : ((t = Thread.currentThread()) instanceof ForkJoinWorkerThread) ?
//                (wt = (ForkJoinWorkerThread) t).pool.awaitJoin(wt.workQueue, this, 0L) :
//                externalAwaitDone();
//    }
//
//    private int doJoin() {
//        int s;
//        Thread t;
//        ForkJoinWorkerThread wt;
//        ForkJoinPool.WorkQueue w;
//        return (s = status) < 0 ? s :
//                ((t = Thread.currentThread()) instanceof ForkJoinWorkerThread) ?
//                        (w = (wt = (ForkJoinWorkerThread) t).workQueue).tryUnpush(this)
//                                && (s = doExec()) < 0 ? s :
//                                wt.pool.awaitJoin(w, this, 0L) :
//                        externalAwaitDone();
//    }
//
//    private static void expungeStaleExceptions() {
//        for (Object x; (x = exceptionTableRefQueue.poll()) != null; ) {
//            if (x instanceof ExceptionNode) {
//                int hashCode = ((ExceptionNode) x).hashCode;
//                ExceptionNode[] t = exceptionTable;
//                int i = hashCode & (t.length - 1);
//                ExceptionNode e = t[i];
//                ExceptionNode pred = null;
//                while (e != null) {
//                    ExceptionNode next = e.next;
//                    if (e == x) {
//                        if (pred == null) {
//                            t[i] = next;
//                        } else {
//                            pred.next = next;
//                        }
//                        break;
//                    }
//                    pred = e;
//                    e = next;
//                }
//            }
//        }
//    }
//
//    private int externalAwaitDone() {
//        int s = ((this instanceof CountedCompleter) ? // try helping
//                java.util.concurrent.ForkJoinPool.common.externalHelpComplete(
//                        (CountedCompleter<?>) this, 0) :
//                java.util.concurrent.ForkJoinPool.common.tryExternalUnpush(this) ? doExec() : 0);
//        if (s >= 0 && (s = status) >= 0) {
//            boolean interrupted = false;
//            do {
//                if (U.compareAndSwapInt(this, STATUS, s, s | SIGNAL)) {
//                    synchronized (this) {
//                        if (status >= 0) {
//                            try {
//                                wait(0L);
//                            } catch (InterruptedException ie) {
//                                interrupted = true;
//                            }
//                        } else
//                            notifyAll();
//                    }
//                }
//            } while ((s = status) >= 0);
//            if (interrupted)
//                Thread.currentThread().interrupt();
//        }
//        return s;
//    }
//
//    private int externalInterruptibleAwaitDone() throws InterruptedException {
//        int s;
//        if (Thread.interrupted())
//            throw new InterruptedException();
//        if ((s = status) >= 0 &&
//                (s = ((this instanceof CountedCompleter) ?
//                        java.util.concurrent.ForkJoinPool.common.externalHelpComplete(
//                                (CountedCompleter<?>) this, 0) :
//                        java.util.concurrent.ForkJoinPool.common.tryExternalUnpush(this) ? doExec() :
//                                0)) >= 0) {
//            while ((s = status) >= 0) {
//                if (U.compareAndSwapInt(this, STATUS, s, s | SIGNAL)) {
//                    synchronized (this) {
//                        if (status >= 0)
//                            wait(0L);
//                        else
//                            notifyAll();
//                    }
//                }
//            }
//        }
//        return s;
//    }
//
//    protected abstract boolean exec();
//
//    public final java.util.concurrent.ForkJoinTask<V> fork() {
//        Thread t;
//        if ((t = Thread.currentThread()) instanceof java.util.concurrent.ForkJoinWorkerThread) {
//            ((java.util.concurrent.ForkJoinWorkerThread) t).workQueue.push(this);
//        } else {
//            java.util.concurrent.ForkJoinPool.common.externalPush(this);
//        }
//        return this;
//    }
//
//    public final V get() throws InterruptedException, ExecutionException {
//        int s = (Thread.currentThread() instanceof java.util.concurrent.ForkJoinWorkerThread) ?
//                doJoin() : externalInterruptibleAwaitDone();
//        Throwable ex;
//        if ((s &= DONE_MASK) == CANCELLED) {
//            throw new CancellationException();
//        }
//        if (s == EXCEPTIONAL && (ex = getThrowableException()) != null) {
//            throw new ExecutionException(ex);
//        }
//        return getRawResult();
//    }
//
//    public final V get(long timeout, TimeUnit unit)
//            throws InterruptedException, ExecutionException, TimeoutException {
//        int s;
//        long nanos = unit.toNanos(timeout);
//        if (Thread.interrupted())
//            throw new InterruptedException();
//        if ((s = status) >= 0 && nanos > 0L) {
//            long d = System.nanoTime() + nanos;
//            long deadline = (d == 0L) ? 1L : d; // avoid 0
//            Thread t = Thread.currentThread();
//            if (t instanceof ForkJoinWorkerThread) {
//                ForkJoinWorkerThread wt = (ForkJoinWorkerThread)t;
//                s = wt.pool.awaitJoin(wt.workQueue, this, deadline);
//            }
//            else if ((s = ((this instanceof CountedCompleter) ?
//                    ForkJoinPool.common.externalHelpComplete(
//                            (CountedCompleter<?>)this, 0) :
//                    ForkJoinPool.common.tryExternalUnpush(this) ?
//                            doExec() : 0)) >= 0) {
//                long ns, ms; // measure in nanosecs, but wait in millisecs
//                while ((s = status) >= 0 &&
//                        (ns = deadline - System.nanoTime()) > 0L) {
//                    if ((ms = TimeUnit.NANOSECONDS.toMillis(ns)) > 0L &&
//                            U.compareAndSwapInt(this, STATUS, s, s | SIGNAL)) {
//                        synchronized (this) {
//                            if (status >= 0)
//                                wait(ms); // OK to throw InterruptedException
//                            else
//                                notifyAll();
//                        }
//                    }
//                }
//            }
//        }
//        if (s >= 0)
//            s = status;
//        if ((s &= DONE_MASK) != NORMAL) {
//            Throwable ex;
//            if (s == CANCELLED)
//                throw new CancellationException();
//            if (s != EXCEPTIONAL)
//                throw new TimeoutException();
//            if ((ex = getThrowableException()) != null)
//                throw new ExecutionException(ex);
//        }
//        return getRawResult();
//    }
//
//    public final Throwable getException() {
//        int s = status & DONE_MASK;
//        return ((s >= NORMAL)    ? null :
//                (s == CANCELLED) ? new CancellationException() :
//                        getThrowableException());
//    }
//
//    public final short getForkJoinTaskTag() {
//        return (short)status;
//    }
//
//    public static ForkJoinPool getPool() {
//        Thread t = Thread.currentThread();
//        return (t instanceof ForkJoinWorkerThread) ?
//                ((ForkJoinWorkerThread) t).pool : null;
//    }
//
//    public static int getQueuedTaskCount() {
//        Thread t; ForkJoinPool.WorkQueue q;
//        if ((t = Thread.currentThread()) instanceof ForkJoinWorkerThread) {
//            q = ((ForkJoinWorkerThread) t).workQueue;
//        } else {
//            q = ForkJoinPool.commonSubmitterQueue();
//        }
//        return (q == null) ? 0 : q.queueSize();
//    }
//
//    public abstract V getRawResult();
//
//    public static int getSurplusQueuedTaskCount() {
//        return ForkJoinPool.getSurplusQueuedTaskCount();
//    }
//
//    private Throwable getThrowableException() {
//        if ((status & DONE_MASK) != EXCEPTIONAL)
//            return null;
//        int h = System.identityHashCode(this);
//        ExceptionNode e;
//        final ReentrantLock lock = exceptionTableLock;
//        lock.lock();
//        try {
//            expungeStaleExceptions();
//            ExceptionNode[] t = exceptionTable;
//            e = t[h & (t.length - 1)];
//            while (e != null && e.get() != this)
//                e = e.next;
//        } finally {
//            lock.unlock();
//        }
//        Throwable ex;
//        if (e == null || (ex = e.ex) == null)
//            return null;
//        if (e.thrower != Thread.currentThread().getId()) {
//            Class<? extends Throwable> ec = ex.getClass();
//            try {
//                Constructor<?> noArgCtor = null;
//                Constructor<?>[] cs = ec.getConstructors();// public ctors only
//                for (int i = 0; i < cs.length; ++i) {
//                    Constructor<?> c = cs[i];
//                    Class<?>[] ps = c.getParameterTypes();
//                    if (ps.length == 0)
//                        noArgCtor = c;
//                    else if (ps.length == 1 && ps[0] == Throwable.class) {
//                        Throwable wx = (Throwable)c.newInstance(ex);
//                        return (wx == null) ? ex : wx;
//                    }
//                }
//                if (noArgCtor != null) {
//                    Throwable wx = (Throwable)(noArgCtor.newInstance());
//                    if (wx != null) {
//                        wx.initCause(ex);
//                        return wx;
//                    }
//                }
//            } catch (Exception ignore) {
//            }
//        }
//        return ex;
//    }
//
//    static final void helpExpungeStaleExceptions() {
//        final ReentrantLock lock = exceptionTableLock;
//        if (lock.tryLock()) {
//            try {
//                expungeStaleExceptions();
//            } finally {
//                lock.unlock();
//            }
//        }
//    }
//
//    public static void helpQuiesce() {
//        Thread t;
//        if ((t = Thread.currentThread()) instanceof ForkJoinWorkerThread) {
//            ForkJoinWorkerThread wt = (ForkJoinWorkerThread)t;
//            wt.pool.helpQuiescePool(wt.workQueue);
//        }
//        else
//            ForkJoinPool.quiesceCommonPool();
//    }
//
//    public static boolean inForkJoinPool() {
//        return Thread.currentThread() instanceof ForkJoinWorkerThread;
//    }
//
//    void internalPropagateException(Throwable ex) {
//    }
//
//    final void internalWait(long timeout) {
//        int s;
//        if ((s = status) >= 0 && // force completer to issue notify
//                U.compareAndSwapInt(this, STATUS, s, s | SIGNAL)) {
//            synchronized (this) {
//                if (status >= 0)
//                    try { wait(timeout); } catch (InterruptedException ie) { }
//                else
//                    notifyAll();
//            }
//        }
//    }
//
//    public final V invoke() {
//        int s;
//        if ((s = doInvoke() & DONE_MASK) != NORMAL)
//            reportException(s);
//        return getRawResult();
//    }
//
//    public static <T extends ForkJoinTask<?>> Collection<T> invokeAll(Collection<T> tasks) {
//        if (!(tasks instanceof RandomAccess) || !(tasks instanceof List<?>)) {
//            invokeAll(tasks.toArray(new ForkJoinTask<?>[tasks.size()]));
//            return tasks;
//        }
//        @SuppressWarnings("unchecked")
//        List<? extends ForkJoinTask<?>> ts =
//                (List<? extends ForkJoinTask<?>>) tasks;
//        Throwable ex = null;
//        int last = ts.size() - 1;
//        for (int i = last; i >= 0; --i) {
//            ForkJoinTask<?> t = ts.get(i);
//            if (t == null) {
//                if (ex == null)
//                    ex = new NullPointerException();
//            }
//            else if (i != 0)
//                t.fork();
//            else if (t.doInvoke() < NORMAL && ex == null)
//                ex = t.getException();
//        }
//        for (int i = 1; i <= last; ++i) {
//            ForkJoinTask<?> t = ts.get(i);
//            if (t != null) {
//                if (ex != null)
//                    t.cancel(false);
//                else if (t.doJoin() < NORMAL)
//                    ex = t.getException();
//            }
//        }
//        if (ex != null)
//            rethrow(ex);
//        return tasks;
//    }
//
//    public static void invokeAll(ForkJoinTask<?> t1, ForkJoinTask<?> t2) {
//        int s1, s2;
//        t2.fork();
//        if ((s1 = t1.doInvoke() & DONE_MASK) != NORMAL)
//            t1.reportException(s1);
//        if ((s2 = t2.doJoin() & DONE_MASK) != NORMAL)
//            t2.reportException(s2);
//    }
//
//    public static void invokeAll(ForkJoinTask<?>... tasks) {
//        Throwable ex = null;
//        int last = tasks.length - 1;
//        for (int i = last; i >= 0; --i) {
//            ForkJoinTask<?> t = tasks[i];
//            if (t == null) {
//                if (ex == null)
//                    ex = new NullPointerException();
//            }
//            else if (i != 0)
//                t.fork();
//            else if (t.doInvoke() < NORMAL && ex == null)
//                ex = t.getException();
//        }
//        for (int i = 1; i <= last; ++i) {
//            ForkJoinTask<?> t = tasks[i];
//            if (t != null) {
//                if (ex != null)
//                    t.cancel(false);
//                else if (t.doJoin() < NORMAL)
//                    ex = t.getException();
//            }
//        }
//        if (ex != null)
//            rethrow(ex);
//    }
//
//    @Override
//    public final boolean isCancelled() {
//        return (status & DONE_MASK) == CANCELLED;
//    }
//
//    public final boolean isCompletedAbnormally() {
//        return status < NORMAL;
//    }
//
//    public final boolean isCompletedNormally() {
//        return (status & DONE_MASK) == NORMAL;
//    }
//
//    @Override
//    public final boolean isDone() {
//        return status < 0;
//    }
//
//    public final V join() {
//        int s;
//        if ((s = doJoin() & DONE_MASK) != NORMAL)
//            reportException(s);
//        return getRawResult();
//    }
//
//    protected static ForkJoinTask<?> peekNextLocalTask() {
//        Thread t; ForkJoinPool.WorkQueue q;
//        if ((t = Thread.currentThread()) instanceof ForkJoinWorkerThread)
//            q = ((ForkJoinWorkerThread)t).workQueue;
//        else
//            q = ForkJoinPool.commonSubmitterQueue();
//        return (q == null) ? null : q.peek();
//    }
//
//    protected static ForkJoinTask<?> pollNextLocalTask() {
//        Thread t;
//        return ((t = Thread.currentThread()) instanceof ForkJoinWorkerThread) ?
//                ((ForkJoinWorkerThread)t).workQueue.nextLocalTask() :
//                null;
//    }
//
//    protected static ForkJoinTask<?> pollTask() {
//        Thread t; ForkJoinWorkerThread wt;
//        return ((t = Thread.currentThread()) instanceof ForkJoinWorkerThread) ?
//                (wt = (ForkJoinWorkerThread)t).pool.nextTaskFor(wt.workQueue) :
//                null;
//    }
//
//    public final void quietlyComplete() {
//        setCompletion(NORMAL);
//    }
//
//    public final void quietlyInvoke() {
//        doInvoke();
//    }
//
//    public final void quietlyJoin() {
//        doJoin();
//    }
//
//    private void readObject(java.io.ObjectInputStream s)
//            throws java.io.IOException, ClassNotFoundException {
//        s.defaultReadObject();
//        Object ex = s.readObject();
//        if (ex != null)
//            setExceptionalCompletion((Throwable)ex);
//    }
//
//    final int recordExceptionalCompletion(Throwable ex) {
//        int s;
//        if ((s = status) >= 0) {
//            int h = System.identityHashCode(this);
//            final ReentrantLock lock = exceptionTableLock;
//            lock.lock();
//            try {
//                expungeStaleExceptions();
//                ExceptionNode[] t = exceptionTable;
//                int i = h & (t.length - 1);
//                for (ExceptionNode e = t[i]; ; e = e.next) {
//                    if (e == null) {
//                        t[i] = new ExceptionNode(this, ex, t[i]);
//                        break;
//                    }
//                    if (e.get() == this) // already present
//                        break;
//                }
//            } finally {
//                lock.unlock();
//            }
//            s = setCompletion(EXCEPTIONAL);
//        }
//        return s;
//    }
//
//    public void reinitialize() {
//        if ((status & DONE_MASK) == EXCEPTIONAL)
//            clearExceptionalCompletion();
//        else
//            status = 0;
//    }
//
//    private void reportException(int s) {
//        if (s == CANCELLED)
//            throw new CancellationException();
//        if (s == EXCEPTIONAL)
//            rethrow(getThrowableException());
//    }
//
//    static void rethrow(Throwable ex) {
//        if (ex != null)
//            ForkJoinTask.<RuntimeException>uncheckedThrow(ex);
//    }
//
//    private int setCompletion(int completion) {
//        for (int s;;) {
//            if ((s = status) < 0)
//                return s;
//            if (U.compareAndSwapInt(this, STATUS, s, s | completion)) {
//                if ((s >>> 16) != 0)
//                    synchronized (this) { notifyAll(); }
//                return completion;
//            }
//        }
//    }
//
//    private int setExceptionalCompletion(Throwable ex) {
//        int s = recordExceptionalCompletion(ex);
//        if ((s & DONE_MASK) == EXCEPTIONAL)
//            internalPropagateException(ex);
//        return s;
//    }
//
//    public final short setForkJoinTaskTag(short tag) {
//        for (int s;;) {
//            if (U.compareAndSwapInt(this, STATUS, s = status,
//                    (s & ~SMASK) | (tag & SMASK)))
//                return (short)s;
//        }
//    }
//
//    protected abstract void setRawResult(V value);
//
//    public boolean tryUnfork() {
//        Thread t;
//        return (((t = Thread.currentThread()) instanceof ForkJoinWorkerThread) ?
//                ((ForkJoinWorkerThread)t).workQueue.tryUnpush(this) :
//                ForkJoinPool.common.tryExternalUnpush(this));
//    }
//
//    @SuppressWarnings("unchecked") static <T extends Throwable>
//    void uncheckedThrow(Throwable t) throws T {
//        throw (T)t; // rely on vacuous cast
//    }
//
//    private void writeObject(java.io.ObjectOutputStream s)
//            throws java.io.IOException {
//        s.defaultWriteObject();
//        s.writeObject(getException());
//    }
//
//
//    // ------------------------------ method ------------------------------
//}
