//package com.ping.wu.collection;
//
//import java.util.AbstractCollection;
//import java.util.Deque;
//
///**
// * @author wuping
// * @date 2020-07-09
// */
//
//public class ArrayDeque<E> extends AbstractCollection<E> implements Deque<E> {
//    private static final int MIN_INITIAL_CAPACITY = 8;
//
//    transient Object[] elements;
//
//    transient int head;
//
//    transient int tail;
//
//    private static int calculateSize(int numElements) {
//        int initialCapacity = MIN_INITIAL_CAPACITY;
//        if (numElements >= MIN_INITIAL_CAPACITY) {
//            initialCapacity = numElements;
//            initialCapacity |= (initialCapacity >>> 1);
//            initialCapacity |= (initialCapacity >>> 2);
//            initialCapacity |= (initialCapacity >>> 4);
//            initialCapacity |= (initialCapacity >>> 8);
//            initialCapacity |= (initialCapacity >>> 16);
//            initialCapacity++;
//            if (initialCapacity < 0) {
//                initialCapacity >>>= 1;
//            }
//        }
//        return initialCapacity;
//    }
//
//    public ArrayDeque() {
//        elements = new Object[16];
//    }
//
//    @Override
//    public boolean add(E e) {
//        addLast(e);
//        return true;
//    }
//
//    @Override
//    public void addFirst(E e) {
//        if (e == null) {
//            throw new NullPointerException();
//        }
//        elements[head = (head - 1) & (elements.length - 1)] = e;
//        if (head == tail) {
//            doubleCapacity();
//        }
//    }
//
//    @Override
//    public void addLast(E e) {
//        if (e == null) {
//            throw new NullPointerException();
//        }
//        elements[tail] = e;
//        if ((tail = (tail + 1) & (elements.length - 1)) == head) {
//            doubleCapacity();
//        }
//    }
//
//    private void allocateElements(int numElements) {
//        elements = new Object[calculateSize(numElements)];
//    }
//
//    @Override
//    public void clear() {
//        int h = head;
//        int t = tail;
//        if (h != t) {
//
//        }
//    }
//}
