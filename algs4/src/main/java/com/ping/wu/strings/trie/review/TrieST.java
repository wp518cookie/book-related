package com.ping.wu.strings.trie.review;

/**
 * @author wuping
 * @date 2020-08-07
 */

public class TrieST<Value> {
    private static final int R = 256;

    private Node root;
    private int n;

    private static class Node {
        private Object val;
        private Node[] next = new Node[R];
    }

    public TrieST() {

    }

    public void put(String key, Value val) {
        if (key == null) {
            throw new IllegalArgumentException("key can't be null");
        }
        if (val == null) {
            delete(key);
        } else {
            root = put(root, key, val, 0);
        }
    }

    private Node put(Node x, String key, Value val, int d) {
        if (x == null) {
            x = new Node();
        }
        if (d == key.length()) {
            if (x.val == null) {
                n++;
            }
            x.val = val;
            return x;
        }
        int t = key.charAt(d);
        x.next[t] = put(x.next[t], key, val, d + 1);
        return x;
    }

    public void delete(String key) {
        if (key == null) {
            throw new IllegalArgumentException("key can't be null");
        }
        root = delete(root, key, 0);
    }

    private Node delete(Node x, String key, int d) {
        if (x == null) {
            return null;
        }
        if (key.length() == d) {
            if (x.val != null) {
                n--;
            }
            x.val = null;
        } else {
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d + 1);
        }
        if (x.val != null) {
            return x;
        }
        for (int c = 0; c < 256; c++) {
            if (x.next[c] != null) {
                return x;
            }
        }
        return null;
    }

    public Value get(String key) {
        if (key == null) {
            throw new IllegalArgumentException("key can't be null");
        }
        Node node =  get(root, key, 0);
        if (node == null) {
            return null;
        } else {
            return (Value) node.val;
        }
    }

    private Node get(Node x, String key, int d) {
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            if (x.val != null) {
                return x;
            } else {
                return null;
            }
        }
        int c = key.charAt(d);
        return get(x.next[c], key, d + 1);
    }
}
