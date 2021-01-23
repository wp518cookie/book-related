//package com.ping.wu.search.c3_1;
//
///**
// * @author wuping
// * @date 2020-06-19
// */
//
//public class RedBlackBST<Key extends Comparable<Key>, Value> {
//    private static final boolean RED = true;
//    private static final boolean BLACK = false;
//    private Node root;
//
//    private class Node {
//        private Key key;
//        private Value val;
//        private Node left, right;
//        private boolean color;
//        private int size;
//
//        public Node(Key key, Value val, boolean color, int size) {
//            this.key = key;
//            this.val = val;
//            this.color = color;
//            this.size = size;
//        }
//    }
//
//    public RedBlackBST() {
//
//    }
//
//    public void delete(Key key) {
//        if (key == null) {
//            throw new IllegalArgumentException();
//        }
//        if (!containsKey(key)) {
//            return;
//        }
//        if (!isRed(root.left) && )
//    }
//
//    public Value get(Key key) {
//        if (key == null) {
//            throw new IllegalArgumentException();
//        }
//        return get(root, key);
//    }
//
//    private Value get(Node x, Key key) {
//        while (x != null) {
//            int comparedResult = key.compareTo(x.key);
//            if (comparedResult > 0) {
//                x = x.right;
//            } else if (comparedResult < 0) {
//                x = x.left;
//            } else {
//                return x.val;
//            }
//        }
//        return null;
//    }
//
//    public void put(Key key, Value val) {
//        if (key == null) {
//            throw new IllegalArgumentException();
//        }
//        if (val == null) {
//            delete(key);
//            return;
//        }
//        root = put(root, key, val);
//        root.color = BLACK;
//    }
//
//    private Node put(Node h, Key key, Value val) {
//        if (h == null) {
//            return new Node(key, val, RED, 1);
//        }
//        int cmp = key.compareTo(h.key);
//        if (cmp < 0) {
//            h.left = put(h.left, key, val);
//        } else if (cmp > 0) {
//            h.right = put(h.right, key, val);
//        } else {
//            h.val = val;
//        }
//        if (isRed(h.right) && !isRed(h.left)) {
//            h = rotateLeft(h);
//        }
//        if (isRed(h.left) && isRed(h.left.left)) {
//            h = rotateRight(h);
//        }
//        if (isRed(h.left) && isRed(h.right)) {
//            flipColors(h);
//        }
//        h.size = size(h.left) + size(h.right) + 1;
//        return h;
//    }
//
//
//
//    private boolean isRed(Node x) {
//        if (x == null) {
//            return false;
//        }
//        return x.color == RED;
//    }
//
//    private int size(Node x) {
//        return x.size;
//    }
//}
