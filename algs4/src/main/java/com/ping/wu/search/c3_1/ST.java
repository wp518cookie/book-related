package com.ping.wu.search.c3_1;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TreeMap;

/**
 * @author wuping
 * @date 2020-06-12
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

    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        return st.containsKey(key);
    }

    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        st.remove(key);
    }

    public Key floor(Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        Key k = st.floorKey(key);
        return k;
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

    public int size() {
        return st.size();
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

    public void put(Key key, Value value) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (value == null) {
            st.remove(key);
        } else {
            st.put(key, value);
        }
    }
}
