package com.ping.wu.sorting.recall;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author wuping
 * @date 2020-04-10
 */

public class IndexMinPQ<Key extends Comparable<Key>> implements Iterable<Integer> {

    private class HeapIterator implements Iterator<Integer> {
        private IndexMinPQ<Key> copy;

        public HeapIterator() {
            copy = new IndexMinPQ(pq.length - 1);
            for (int i = 1; i <= n; i++) {
                copy.insert(pq[i], keys[pq[i]]);
            }
        }

        @Override
        public boolean hasNext() {
            return !copy.isEmpty();
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return copy.delMin();
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new HeapIterator();
    }

    private int maxN;
    private int n;
    private int[] pq;
    private int[] qp;
    private Key[] keys;

    public IndexMinPQ(int maxN) {
        this.maxN = maxN;
        pq = new int[maxN + 1];
        qp = new int[maxN + 1];
        for (int i = 0; i <= maxN; i++) {
            qp[i] = -1;
        }
        keys = (Key[]) new Comparable[maxN + 1];
    }

    public boolean contains(int idx) {
        idxCheck(idx);
        return qp[idx] != -1;
    }

    public void insert(int idx, Key key) {
        idxCheck(idx);
        n++;
        qp[idx] = n;
        pq[n] = idx;
        keys[idx] = key;
        swim(n);
    }

    public void changeKey(int idx, Key key) {
        idxCheck(idx);
        keys[idx] = key;
        swim(qp[idx]);
        sink(qp[idx]);
    }

    public void decreaseKey(int idx, Key key) {
        if (keys[idx].compareTo(key) <= 0) {
            throw new IllegalArgumentException("original val is lower than param");
        }
        idxCheck(idx);
        keys[idx] = key;
        swim(qp[idx]);
    }

    public void delete(int i) {
        if (!contains(i)) {
            throw new NoSuchElementException();
        }
        int index = qp[i];
        exch(index, n--);
        swim(index);
        sink(index);
        keys[i] = null;
        qp[i] = -1;
    }

    public int delMin() {
        if (n == 0) {
            throw new NoSuchElementException();
        }
        int first = pq[1];
        delete(first);
        return first;
    }

    private boolean greater(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }

    public void increaseKey(int i, Key key) {
        if (keys[i].compareTo(key) >= 0) {
            throw new IllegalArgumentException("original val is bigger than param");
        }
        idxCheck(i);
        keys[i] = key;
        sink(qp[i]);
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public Key keyOf(int i) {
        return keys[i];
    }

    public int minIndex() {
        return pq[1];
    }

    public Key minKey() {
        return keys[pq[1]];
    }

    public int size() {
        return n;
    }

    private void sink(int k) {
        int j = 2 * k;
        while (j <= n) {
            if (j < n && greater(j, j + 1)) {
                j = j + 1;
            }
            if (greater(k, j)) {
                exch(k, j);
                j = 2 * k;
            } else {
                break;
            }
        }
    }

    private void swim(int k) {
        int j = k / 2;
        while (j >= 1) {
            if (!greater(j, k)) {
                break;
            }
            exch(j, k);
            j = j / 2;
        }
    }

    public void exch(int i, int j) {
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    private void idxCheck(int idx) {
        if (idx < 0 || idx >= maxN) {
            throw new IllegalArgumentException();
        }
    }

    public static void main(String[] args) {
        IndexMinPQ<String> pq = new IndexMinPQ(5);
        pq.insert(2, "a");
        pq.insert(1, "b");
        pq.insert(4, "c");
        pq.insert(0, "d");
        pq.insert(3, "e");

        pq.increaseKey(1, "f");
        pq.decreaseKey(0, "b");
        while (!pq.isEmpty()) {
            System.out.println(pq.delMin());
        }
    }
}
