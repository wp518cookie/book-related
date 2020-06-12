//package com.ping.wu.juc;
//
//import java.lang.ref.WeakReference;
//import java.util.Objects;
//import java.util.concurrent.atomic.AtomicInteger;
//import java.util.function.Supplier;
//
///**
// * @author wuping
// * @date 2020-06-02
// */
//
//public class MyThreadLocal<T> {
//    // ------------------------ field start ------------------------
//    private static final int HASH_INCREMENT = 0x61c88647;
//    private static AtomicInteger nextHashCode = new AtomicInteger();
//    private final int threadLocalHashCode = nextHashCode();
//
//    // ------------------------ field end   ------------------------
//    // ------------------------ method start------------------------
//    private static int nextHashCode() {
//        return nextHashCode.getAndAdd(HASH_INCREMENT);
//    }
//
//    // ------------------------ method end  ------------------------
//    // ------------------------ class start ------------------------
//    static class ThreadLocalMap {
//        private static final int INITIAL_CAPACITY = 16;
//        private Entry[] table;
//        private int size = 0;
//        private int threshold;
//
//        ThreadLocalMap(MyThreadLocal<?> firstKey, Object firstValue) {
//            table = new Entry[INITIAL_CAPACITY];
//            int i = firstKey.threadLocalHashCode & (INITIAL_CAPACITY - 1);
//            table[i] = new Entry(firstKey, firstValue);
//            size = 1;
//            setThreshold(INITIAL_CAPACITY);
//        }
//
//        private ThreadLocalMap(ThreadLocalMap parentMap) {
//            Entry[] parentTable = parentMap.table;
//            int len = parentTable.length;
//            setThreshold(len);
//            table = new MyThreadLocal.ThreadLocalMap.Entry[len];
//
//            for (int j = 0; j < len; j++) {
//                MyThreadLocal.ThreadLocalMap.Entry e = parentTable[j];
//                if (e != null) {
//                    @SuppressWarnings("unchecked")
//                    MyThreadLocal<Object> key = (MyThreadLocal<Object>) e.get();
//                    if (key != null) {
//                        Object value = key.childValue(e.value);
//                        MyThreadLocal.ThreadLocalMap.Entry c = new MyThreadLocal.ThreadLocalMap.Entry(key, value);
//                        int h = key.threadLocalHashCode & (len - 1);
//                        while (table[h] != null) {
//                            h = nextIndex(h, len);
//                        }
//                        table[h] = c;
//                        size++;
//                    }
//                }
//            }
//        }
//
//        private void setThreshold(int len) {
//            threshold = len * 2 / 3;
//        }
//
//        static class Entry extends WeakReference<MyThreadLocal<?>> {
//            Object value;
//
//            Entry(MyThreadLocal<?> k, Object v) {
//                super(k);
//                value = v;
//            }
//        }
//    }
//
//    static final class SuppliedThreadLocal<T> extends MyThreadLocal<T> {
//        private final Supplier<? extends T> supplier;
//
//        SuppliedThreadLocal(Supplier<? extends T> supplier) {
//            this.supplier = Objects.requireNonNull(supplier);
//        }
//
//        @Override
//        protected T initialValue() {
//            return supplier.get();
//        }
//    }
//    // ------------------------ class end   ------------------------
//}
