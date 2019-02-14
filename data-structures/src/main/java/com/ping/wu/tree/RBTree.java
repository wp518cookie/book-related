//package com.ping.wu.tree;
//
///**
// * @author wuping
// * @date 2019/2/13
// */
//
//public class RBTree<T extends Comparable<T>> {
//    private RBTNode<T> root;
//    private static final boolean RED = false;
//    private static final boolean BLACK = true;
//
//    private static class RBTNode<T extends Comparable<T>> {
//        boolean color;
//        T key;
//        RBTNode<T> left;
//        RBTNode<T> right;
//        RBTNode<T> parent;
//
//        public RBTNode(T key, boolean color, RBTNode<T> parent, RBTNode<T> left, RBTNode<T> right) {
//            this.key = key;
//            this.color = color;
//            this.parent = parent;
//            this.left = left;
//            this.right = right;
//        }
//
//        public T getKey() {
//            return key;
//        }
//
//        @Override
//        public String toString() {
//            return "" + key + (this.color == RED ? "(R)" : "B");
//        }
//    }
//
//    public RBTree() {
//        root = null;
//    }
//
//    private RBTNode<T> parentOf(RBTNode<T> node) {
//        return node != null ? node.parent : null;
//    }
//
//    private boolean colorOf(RBTNode<T> node) {
//        return node != null ? node.color : BLACK;
//    }
//
//    private boolean isRed(RBTNode<T> node) {
//        return node != null && node.color == RED;
//    }
//
//    private boolean isBlack(RBTNode<T> node) {
//        return !isRed(node);
//    }
//
//    private void setBlack(RBTNode<T> node) {
//        if (node != null) {
//            node.color = BLACK;
//        }
//    }
//
//    private void setRed(RBTNode<T> node) {
//        if (node != null) {
//            node.color = RED;
//        }
//    }
//
//    private void setParent(RBTNode<T> node, RBTNode<T> parent) {
//        if (node != null) {
//            node.parent = parent;
//        }
//    }
//
//    public void insert(T key) {
//        if (key == null) {
//            throw new NullPointerException();
//        }
//        RBTNode<T> n = root;
//        if (n == null) {
//            root = new RBTNode(key, BLACK, null, null, null);
//            return;
//        }
//        while (true) {
//            int comparisonResult = key.compareTo(n.key);
//            if (comparisonResult == 0) {
//                return;
//            } else if (comparisonResult > 0) {
//                if (n.right == null) {
//                    n.right = new RBTNode(key, RED, n, null, null);
//                    adjustAfterInsertion(n.right);
//                    break;
//                }
//                n = n.right;
//            } else {
//                if (n.left == null) {
//                    n.left = new RBTNode(key, RED, n, null, null);
//                    adjustAfterInsertion(n.left);
//                    break;
//                }
//                n = n.left;
//            }
//        }
//    }
//
//    public void adjustAfterInsertion(RBTNode<T> node) {
//        if (node == root) {
//            setBlack(node);
//        }
//        RBTNode<T> parentNode = node.parent;
//        if (parentNode != null && isRed(parentNode)) {
//            RBTNode<T> uncleNode = getUncle(node);
//            RBTNode<T> grandParentNode = parentNode.parent;
//            if (isRed(uncleNode)) {
//                setBlack(parentNode);
//                setBlack(uncleNode);
//                setRed(grandParentNode);
//                adjustAfterInsertion(grandParentNode);
//            } else {
//                if (isLeftNode(parentNode)) {
//                    if (isRightNode(node)) {
//                        rotateLeft(parentNode);
//                    }
//                    setBlack(node);
//                    setRed(grandParentNode);
//                    rotateRight(grandParentNode);
//                } else {
//                    if (isLeftNode(node)) {
//                        rotateRight(parentNode);
//                    }
//                    setBlack(node);
//                    setRed(grandParentNode);
//                    rotateLeft(grandParentNode);
//                }
//            }
//        }
//    }
//
//    public void remove(T key) {
//        RBTNode<T> node = search(key);
//        if (node == null) {
//            return;
//        }
//        if (node == root) {
//            root = null;
//        }
//        if (node.left == null && node.right == null && isBlack(node)) {
//            adjustAfterRemoval(node);
//            return;
//        }
//        if (node.left == null || node.right == null) {
//            RBTNode replace = node.left == null ? node.right : node.left;
//            if (node.parent.left == node) {
//                node.parent.left = replace;
//            } else {
//                node.parent.right = replace;
//            }
//            replace.parent = node.parent;
//            if (isLeftNode(replace)) {
//                node.left = null;
//            } else {
//                node.right = null;
//            }
//            if (isBlack(node)) {
//                adjustAfterRemoval(replace);
//            }
//            return;
//        }
//        RBTNode<T> successor = successor(node);
//        node.key = successor.key;
//        if (isBlack(successor)) {
//            adjustAfterRemoval(successor);
//        }
//    }
//
//    public void adjustAfterRemoval(RBTNode<T> n) {
//        while (n != root && isBlack(n)) {
//            // 左节点
//            RBTNode<T> parent = n.parent;
//            RBTNode<T> uncle = getUncle(n);
//            if (isLeftNode(n)) {
//                if (isBlack(parent)) {
//                    if (isRightNode(n)) {
//                        rotateRight(parent);
//                    }
//                }
//            } else {
//
//            }
//        }
//    }
//
//    public RBTNode<T> successor(RBTNode<T> node) {
//        if (node.left == null) {
//            return null;
//        }
//        RBTNode<T> successor = node.left;
//        while (successor.right != null) {
//            successor = successor.right;
//        }
//        return successor;
//    }
//
//    public RBTNode<T> search(T key) {
//        return search(root, key);
//    }
//
//    public RBTNode<T> search(RBTNode<T> node, T key) {
//        if (node == null || key == null) {
//            return null;
//        }
//        while (node != null) {
//            int comparisonResult = key.compareTo(node.key);
//            if (comparisonResult == 0) {
//                return node;
//            } else if (comparisonResult > 0) {
//                node = node.right;
//            } else {
//                node = node.left;
//            }
//        }
//        return null;
//    }
//
//    public boolean isLeftNode(RBTNode<T> node) {
//        if (node.parent == null) {
//            return false;
//        }
//        return node.parent.left == node;
//    }
//
//    public boolean isRightNode(RBTNode<T> node) {
//        if (node.parent == null) {
//            return false;
//        }
//        return node.parent.right == node;
//    }
//
//    public RBTNode<T> getUncle(RBTNode<T> node) {
//        RBTNode<T> parent;
//        RBTNode<T> grandParent;
//        if (node != null &&
//                (parent = node.parent) != null &&
//                (grandParent = node.parent.parent) != null) {
//            return parent == grandParent.left ? grandParent.right : grandParent.left;
//        }
//    }
//
//    private void rotateLeft(RBTNode<T> node) {
//        if (node == null || node.right == null) {
//            return;
//        }
//        RBTNode<T> oldRight = node.right;
//        node.right = oldRight.left;
//        oldRight.left = node;
//        if (node == root) {
//            root = oldRight;
//            oldRight.parent = null;
//            node.parent = oldRight;
//        } else {
//            if (node.parent.left == node) {
//                node.parent.left = oldRight;
//            } else {
//                node.parent.right = oldRight;
//            }
//            oldRight.parent = node.parent;
//            node.parent = oldRight;
//        }
//    }
//
//    private void rotateRight(RBTNode<T> node) {
//        if (node == null || node.left == null) {
//            return;
//        }
//        RBTNode<T> oldLeft = node.left;
//        node.left = oldLeft.right;
//        oldLeft.right = node;
//        if (node == root) {
//            root = oldLeft;
//            oldLeft.parent = null;
//            node.parent = oldLeft;
//        } else {
//            if (node.parent.left == node) {
//                node.parent.left = oldLeft;
//            } else {
//                node.parent.right = oldLeft;
//            }
//            oldLeft.parent = node.parent;
//            node.parent = oldLeft;
//        }
//    }
//}
