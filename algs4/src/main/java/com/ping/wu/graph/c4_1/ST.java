package com.ping.wu.graph.c4_1;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TreeMap;

/**
 * @author wuping
 * @date 2019-06-25
 */

public class ST<Key extends Comparable<Key>, Value> implements Iterable<Key> {
    private TreeMap<Key, Value> st;

    public ST() {
        st = new TreeMap();
    }

    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        return st.get(key);
    }

    public void put(Key key, Value val) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (val == null) {
            st.remove(key);
        } else {
            st.put(key, val);
        }
    }

    public void delet(Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        st.remove(key);
    }

    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        return st.containsKey(key);
    }

    public int size() {
        return st.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Iterable<Key> keys() {
        return st.keySet();
    }

    @Override
    public Iterator<Key> iterator() {
        return st.keySet().iterator();
    }

    public Key min() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return st.firstKey();
    }

    public Key max() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return st.lastKey();
    }

    public Key ceiling(Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        Key k = st.ceilingKey(key);
        if (k == null) {
            throw new NoSuchElementException();
        }
        return k;
    }

    public Key floor(Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        Key k = st.floorKey(key);
        if (k == null) {
            throw new NoSuchElementException();
        }
        return k;
    }
}
