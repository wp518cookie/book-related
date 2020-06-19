package com.ping.wu.search.c3_1;

/**
 * @author wuping
 * @date 2020-06-18
 */

public class MyBST<Key extends Comparable<Key>, Value> {
    private Node root;

    public static void main(String[] args) {
        MyBST<Integer, Integer> myBST = new MyBST<>();
        myBST.put(2, 2);
        myBST.put(1, 1);
        myBST.put(4, 4);
        myBST.put(3, 3);
        System.out.println(123);
        myBST.delete(4);
        System.out.println(123);
    }

    public void put(Key key, Value value) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (value == null) {
            delete(key);
            return;
        }
        if (root == null) {
            root = new Node(key, value, 0);
            return;
        }
        put(root, key, value);
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            return new Node(key, value, 0);
        }
        int comparedR = key.compareTo(node.key);
        if (comparedR < 0) {
            node.left = put(node.left, key, value);
        } else if (comparedR > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    public int size(Node node) {
        if (node == null) {
            return 0;
        } else {
            return node.size;
        }
    }

    private Node min(Node t) {
        if (t.left == null) {
            return t;
        } else {
            return min(t.left);
        }
    }

    private Node max(Node t) {
        if (t.right == null) {
            return t;
        }
        return max(t.right);
    }

    public void delete(Key key) {
        delete(root, key);
    }

    public Node deleteMin(Node n) {
        if (n.left == null) {
            return n.right;
        }
        return deleteMin(n.left);
    }

    public Node delete(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int compared = key.compareTo(node.key);
        if (compared > 0) {
            node.right = delete(node.right, key);
        } else if (compared < 0) {
            node.left = delete(node.left, key);
        } else {
            if (node.left == null && node.right == null) {
                return null;
            }
            if (node.left == null || node.right == null) {
                return node.left == null ? node.right : node.left;
            }
            Node x = node;
            node = min(node.right);
            node.left = x.left;
            node.right = deleteMin(x.right);
        }
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    private class Node {
        private Key key;
        private Value value;
        private Node left, right;
        private int size;

        public Node(Key key, Value val, int size) {
            this.key = key;
            this.value = val;
            this.size = size;
        }
    }
}
