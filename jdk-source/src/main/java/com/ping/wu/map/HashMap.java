//package com.ping.wu.map;
//
//import java.io.Serializable;
//import java.util.Map;
//import java.util.Objects;
//import java.util.Set;
//
///**
// * @author wuping
// * @date 2019/2/13
// */
//
//public class HashMap<K, V> extends AbstractMap<K, V> implements Map<K, V>, Serializable {
//    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
//
//    static final int MAXIMUM_CAPACITY = 1 << 30;
//
//    static final float DEFAULT_LOAD_FACTOR = 0.75f;
//
//    static final int TREEIFY_THRESHOLD = 8;
//
//    static final int UNTREEIFY_THRESHOLD = 6;
//
//    static final int MIN_TREEIFY_CAPACITY = 64;
//
//    transient Set<Entry<K, V>> entrySet;
//
//    transient HashMap.Node<K, V>[] table;
//
//    transient int size;
//
//    transient int modCount;
//
//    int threshold;
//
//    final float loadFactor;
//
//    public HashMap() {
//        this.loadFactor = DEFAULT_LOAD_FACTOR; // all other fields defaulted
//    }
//
//    @Override
//    public V put(K key, V value) {
//        return putVal(hash(key), key, value, false, true);
//    }
//
//
//    //是低位高位都能参与作用
//    static final int hash(Object key) {
//        int h;
//        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
//    }
//
//    static class Node<K, V> implements Map.Entry<K, V> {
//        final int hash;
//        final K key;
//        V value;
//        HashMap.Node<K, V> next;
//
//        Node(int hash, K key, V value, Node<K, V> next) {
//            this.hash = hash;
//            this.key = key;
//            this.value = value;
//            this.next = next;
//        }
//
//        @Override
//        public final K getKey() {
//            return key;
//        }
//
//        @Override
//        public final V getValue() {
//            return value;
//        }
//
//        @Override
//        public final String toString() {
//            return key + "=" + value;
//        }
//
//        @Override
//        public final int hashCode() {
//            return Objects.hashCode(key) ^ Objects.hashCode(value);
//        }
//
//        @Override
//        public final V setValue(V newValue) {
//            V oldValue = value;
//            value = newValue;
//            return oldValue;
//        }
//
//        @Override
//        public final boolean equals(Object o) {
//            if (o == this) {
//                return true;
//            }
//            if (o instanceof Map.Entry) {
//                Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
//                if (Objects.equals(key, e.getKey()) &&
//                        Objects.equals(value, e.getValue())) {
//                    return true;
//                }
//            }
//            return false;
//        }
//    }
//}
