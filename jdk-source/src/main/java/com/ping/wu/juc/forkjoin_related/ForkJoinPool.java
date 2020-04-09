//package com.ping.wu.juc.forkjoin_related;
//
//import com.ping.wu.juc.AbstractExecutorService;
//import com.ping.wu.util.UnsafeGenerator;
//import com.sun.corba.se.spi.orbutil.threadpool.WorkQueue;
//
//import java.security.AccessControlContext;
//import java.security.Permissions;
//import java.security.ProtectionDomain;
//import java.util.concurrent.ForkJoinTask;
//import java.util.concurrent.ForkJoinWorkerThread;
//
///**
// * @author wuping
// * @date 2020-03-30
// */
//
//@sun.misc.Contended
//public class ForkJoinPool extends AbstractExecutorService {
//    /**
//     * ------------------------------field start------------------------------
//     */
//    static final int SMASK          = 0xffff;
//    static final int MAX_CAP        = 0x7fff;
//    static final int EVENMASK       = 0xfffe;
//    static final int SQMASK         = 0x007e;
//    private static final int DEFAULT_COMMON_MAX_SPARES = 256;
//
//    private static int commonMaxSpares;
//    private static final RuntimePermission modifyThreadPermission;
//
//    public static final java.util.concurrent.ForkJoinPool.ForkJoinWorkerThreadFactory
//            defaultForkJoinWorkerThreadFactory;
//    static final ForkJoinPool common;
//
//
//    private static final sun.misc.Unsafe U;
//    private static final int  ABASE;
//    private static final int  ASHIFT;
//    private static final long CTL;
//    private static final long RUNSTATE;
//    private static final long STEALCOUNTER;
//    private static final long PARKBLOCKER;
//    private static final long QTOP;
//    private static final long QLOCK;
//    private static final long QSCANSTATE;
//    private static final long QPARKER;
//    private static final long QCURRENTSTEAL;
//    private static final long QCURRENTJOIN;
//
//    static {
//        try {
//            U = UnsafeGenerator.getUnsafe();
//            Class<?> k = ForkJoinPool.class;
//            CTL = U.objectFieldOffset(k.getDeclaredField("ctl"));
//            RUNSTATE = U.objectFieldOffset(k.getDeclaredField("runState"));
//            STEALCOUNTER = U.objectFieldOffset(k.getDeclaredField("stealCounter"));
//            Class<?> tk = Thread.class;
//            PARKBLOCKER = U.objectFieldOffset(k.getDeclaredField("parkBlocker"));
//            Class<?> wk = WorkQueue.class;
//            QTOP = U.objectFieldOffset(wk.getDeclaredField("top"));
//            QLOCK = U.objectFieldOffset(wk.getDeclaredField("qlock"));
//            QSCANSTATE = U.objectFieldOffset(wk.getDeclaredField("scanState"));
//            QPARKER = U.objectFieldOffset(wk.getDeclaredField("parker"));
//            QCURRENTSTEAL = U.objectFieldOffset(wk.getDeclaredField("currentSteal"));
//            QCURRENTJOIN = U.objectFieldOffset(wk.getDeclaredField("currentJoin"));
//            Class<?> ak = ForkJoinTask[].class;
//            ABASE = U.arrayBaseOffset(ak);
//            int scale = U.arrayIndexScale(ak);
//            if ((scale & (scale - 1)) != 0) {
//                throw new Error("data type scale not a power of two");
//            }
//            ASHIFT = 31 - Integer.numberOfLeadingZeros(scale);
//        } catch (Exception e) {
//            throw new Error(e);
//        }
//
//        commonMaxSpares = DEFAULT_COMMON_MAX_SPARES;
//        defaultForkJoinWorkerThreadFactory =
//                new DefaultForkJoinWorkerThreadFactory();
//        modifyThreadPermission = new RuntimePermission("modifyThread");
//
//        common = java.security.AccessController.doPrivileged
//                (new java.security.PrivilegedAction<ForkJoinPool>() {
//                    public ForkJoinPool run() { return makeCommonPool(); }});
//        int par = common.config & SMASK; // report 1 even if threads disabled
//        commonParallelism = par > 0 ? par : 1;
//    }
//    /**
//     * ------------------------------field end------------------------------
//     */
//
//    //--------------------------constructor start--------------------------
//    public ForkJoinPool() {
//        this(Math.min(MAX_CAP, Runtime.getRuntime().availableProcessors()),
//                defaultForkJoinWorkerThreadFactory, null, false);
//    }
//
//    //--------------------------constructor end--------------------------
//
//    //--------------------------method start--------------------------
//
//    private static ForkJoinPool makeCommonPool() {
//        int parallelism = -1;
//        ForkJoinWorkerThreadFactory factory = null;
//        UncaughtExceptionHandler handler = null;
//        try {  // ignore exceptions in accessing/parsing properties
//            String pp = System.getProperty
//                    ("java.util.concurrent.ForkJoinPool.common.parallelism");
//            String fp = System.getProperty
//                    ("java.util.concurrent.ForkJoinPool.common.threadFactory");
//            String hp = System.getProperty
//                    ("java.util.concurrent.ForkJoinPool.common.exceptionHandler");
//            if (pp != null)
//                parallelism = Integer.parseInt(pp);
//            if (fp != null)
//                factory = ((ForkJoinWorkerThreadFactory)ClassLoader.
//                        getSystemClassLoader().loadClass(fp).newInstance());
//            if (hp != null)
//                handler = ((UncaughtExceptionHandler)ClassLoader.
//                        getSystemClassLoader().loadClass(hp).newInstance());
//        } catch (Exception ignore) {
//        }
//        if (factory == null) {
//            if (System.getSecurityManager() == null)
//                factory = defaultForkJoinWorkerThreadFactory;
//            else // use security-managed default
//                factory = new InnocuousForkJoinWorkerThreadFactory();
//        }
//        if (parallelism < 0 && // default 1 less than #cores
//                (parallelism = Runtime.getRuntime().availableProcessors() - 1) <= 0)
//            parallelism = 1;
//        if (parallelism > MAX_CAP)
//            parallelism = MAX_CAP;
//        return new ForkJoinPool(parallelism, factory, handler, LIFO_QUEUE,
//                "ForkJoinPool.commonPool-worker-");
//    }
//
//
//    //--------------------------method end--------------------------
//
//    //--------------------------static class start--------------------------
//    public static interface ForkJoinWorkerThreadFactory {
//        public ForkJoinWorkerThread newThread(java.util.concurrent.ForkJoinPool pool);
//    }
//
//    static final class DefaultForkJoinWorkerThreadFactory
//            implements java.util.concurrent.ForkJoinPool.ForkJoinWorkerThreadFactory {
//        public final ForkJoinWorkerThread newThread(java.util.concurrent.ForkJoinPool pool) {
//            return new ForkJoinWorkerThread(pool);
//        }
//    }
//
//    static final class InnocuousForkJoinWorkerThreadFactory
//            implements java.util.concurrent.ForkJoinPool.ForkJoinWorkerThreadFactory {
//
//        /**
//         * An ACC to restrict permissions for the factory itself.
//         * The constructed workers have no permissions set.
//         */
//        private static final AccessControlContext innocuousAcc;
//        static {
//            Permissions innocuousPerms = new Permissions();
//            innocuousPerms.add(modifyThreadPermission);
//            innocuousPerms.add(new RuntimePermission(
//                    "enableContextClassLoaderOverride"));
//            innocuousPerms.add(new RuntimePermission(
//                    "modifyThreadGroup"));
//            innocuousAcc = new AccessControlContext(new ProtectionDomain[] {
//                    new ProtectionDomain(null, innocuousPerms)
//            });
//        }
//
//        public final ForkJoinWorkerThread newThread(java.util.concurrent.ForkJoinPool pool) {
//            return (ForkJoinWorkerThread.InnocuousForkJoinWorkerThread)
//                    java.security.AccessController.doPrivileged(
//                            new java.security.PrivilegedAction<ForkJoinWorkerThread>() {
//                                public ForkJoinWorkerThread run() {
//                                    return new ForkJoinWorkerThread.
//                                            InnocuousForkJoinWorkerThread(pool);
//                                }}, innocuousAcc);
//        }
//    }
//
//    @sun.misc.Contended
//    static final class WorkQueue {
//
//    }
//
//    //--------------------------static class end--------------------------
//}
