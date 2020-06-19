package com.ping.wu.search.c3_1;

/**
 * @author wuping
 * @date 2020-06-12
 */

public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private static final int INIT_CAPACITY = 2;
    private Key[] keys;
    private Value[] vals;
    private int n = 0;

    public BinarySearchST() {
        this(INIT_CAPACITY);
    }

    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    public void put(Key key, Value val) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (val == null) {
            delete(key);
            return;
        }
        int i = rank(key);
        if (i < n || keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        }
        if (n == keys.length) {
            resize(2 * keys.length);
        }
        for (int j = n; j > i; j--) {
            keys[i] = keys[i - 1];
            vals[i] = vals[i - 1];
        }
        keys[i] = key;
        vals[i] = val;
        n++;
        assert check();
    }

    public int rank(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to rank() is null");
        }
        int low = 0;
        int high = n;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comparedResult = key.compareTo(keys[mid]);
            if (comparedResult < 0) {
                high = mid - 1;
            } else if (comparedResult > 0) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return low;
    }

    private void resize(int capacity) {
        Key[] tempK = (Key[]) new Comparable[capacity];
        Value[] tempV = (Value[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            tempK[i] = keys[i];
            tempV[i] = vals[i];
        }
        vals = tempV;
        keys = tempK;
    }

    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (isEmpty()) {
            return;
        }
        int i = rank(key);
        if (i == n || keys[i].compareTo(key) != 0) {
            return;
        }
        for (int j = i + 1; j < n; j++) {
            keys[j - 1] = keys[j];
            vals[j - 1] = vals[j];
        }
        n--;
        keys[n] = null;
        vals[n] = null;
        if (n > 0 && n == keys.length / 4) {
            resize(keys.length / 2);
        }
        assert check();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    private boolean check() {
        return isSorted() && rankCheck();
    }

    private boolean rankCheck() {
        for (int i = 0; i < size(); i++) {
            if (i != rank(select(i))) {
                return false;
            }
        }
        for (int i = 0; i < size(); i++) {
            if (keys[i].compareTo(select(rank(keys[i]))) != 0) {
                return false;
            }
        }
        return true;
    }

    private boolean isSorted() {
        for (int i = 1; i < size(); i++) {
            if (keys[i].compareTo(keys[i - 1]) < 0) {
                return false;
            }
        }
        return true;
    }

    public Key select(int k) {
        if (k < 0 || k >= size()) {
            throw new IllegalArgumentException();
        }
        return keys[k];
    }

    public int size() {
        return n;
    }
}
