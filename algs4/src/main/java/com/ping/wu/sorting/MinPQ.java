package com.ping.wu.sorting;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author wuping
 * @date 2020-04-02
 */

public class MinPQ<Key> {
    private int n;
    private Comparator<Key> comparator;
    private Key[] pq;

    public MinPQ() {
        this(1);
    }

    public MinPQ(int initialCapacity) {
        n = 0;
        pq = (Key[]) new Object[initialCapacity + 1];
    }

    public MinPQ(Comparator<Key> comparator) {
        this.n = 0;
        this.comparator = comparator;
        pq = (Key[]) new Object[2];
    }

    public MinPQ(int initialCapacity, Comparator<Key> comparator) {
        this.n = 0;
        this.comparator = comparator;
        pq = (Key[]) new Object[initialCapacity + 1];
    }

    public MinPQ(Key[] keys) {
        pq = (Key[]) new Object[keys.length + 1];
        n = keys.length;
        for (int i = 1; i < keys.length + 1; i++) {
            pq[i] = keys[i - 1];
        }
    }

    public Key delMin() {
        Key result = pq[1];
        pq[1] = pq[n--];
        pq[n + 1] = null;
        sink(1);
        if (n < pq.length / 4) {
            resize(pq.length / 2);
        }
        return result;
    }

    private void exch(int i, int j) {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    public boolean greater(int i, int j) {
        if (comparator == null) {
            return ((Comparable<Key>) pq[i]).compareTo(pq[j]) > 0;
        } else {
            return comparator.compare(pq[i], pq[j]) > 0;
        }
    }

    public void insert(Key key) {
        if (n >= pq.length - 1) {
            resize(pq.length * 2);
        }
        n++;
        pq[n] = key;
        swim(n);
    }

    public boolean isEmpty() {
        return n == 0;
    }

    private boolean isMinHeap() {
        return isMinHeap(1);
    }

    private boolean isMinHeap(int i) {
        if (i > n) {
            return true;
        }
        int left = i * 2;
        int right = i * 2 + 1;
        if (left <= n && greater(i, left)) {
            return false;
        }
        if (right <= n && greater(i, right)) {
            return false;
        }
        return isMinHeap(left) && isMinHeap(right);
    }

    public Iterator<Key> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Key> {
        private MinPQ<Key> copy;

        public HeapIterator() {
            copy = new MinPQ(n);
            for (int i = 0; i < n; i++) {
                copy.insert(pq[i + 1]);
            }
        }

        @Override
        public boolean hasNext() {
            return !copy.isEmpty();
        }

        @Override
        public Key next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return copy.delMin();
        }
    }

    private void resize(int capacity) {
        assert capacity > 0;
        Key[] newPq = (Key[]) new Object[capacity];
        for (int i = 1; i <= n; i++) {
            newPq[i] = pq[i];
        }
        pq = newPq;
    }

    private void sink(int i) {
        while (true) {
            int left = i * 2;
            if (left > n) {
                break;
            }
            if (left < n && greater(left, left + 1)) {
                left++;
            }
            if (greater(i, left)) {
                exch(i, left);
                i = left;
            } else {
                break;
            }
        }
    }

    private void swim(int i) {
        while (i > 1) {
            int parent = i / 2;
            if (greater(parent, i)) {
                exch(parent, i);
                i = parent;
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        MinPQ<Integer> pq = new MinPQ();
        int[] arr = new int[]{3, 4, 1, 34, 5, 9, 10};
        for (int i : arr) {
            pq.insert(i);
        }
        while (!pq.isEmpty()) {
            System.out.println(pq.delMin());
        }
    }
}
