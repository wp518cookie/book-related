package com.ping.wu.tree;

import java.util.Comparator;

/**
 * @author wuping
 * @date 2019/2/12
 */

public class BinarySearchTree<E> {
    protected BinaryTreeNode<E> root = null;

    private Comparator<E> comparator;

    public BinarySearchTree() {
        this(null);
    }

    public BinarySearchTree(Comparator<E> c) {
        this.comparator = c;
    }

    public boolean contains(E data) {
        return nodeContaining(data) != null;
    }

    public void add(E data) {
        if (root == null) {
            root = new LinkedBinaryTreeNode<E>(data);
        }
        BinaryTreeNode<E> n = root;
        while (true) {
            int comparisonResult = compare(data, n.getData());
            if (comparisonResult == 0) {
                n.setData(data);
                return;
            } else if (comparisonResult < 0) {
                if (n.getLeft() == null) {
                    n.setLeft(new LinkedBinaryTreeNode<E>(data));
                    return;
                }
                n = n.getLeft();
            } else {
                if (n.getRight() == null) {
                    n.setRight(new LinkedBinaryTreeNode<E>(data));
                    return;
                }
                n = n.getRight();
            }
        }
    }

    public void remove(E data) {
        BinaryTreeNode<E> node = nodeContaining(data);
        if (node == null) {
            return;
        } else if (node.getLeft() != null && node.getRight() != null) {
            BinaryTreeNode<E> n = predecessor(node);
            if (n.getParent() == node) {
                node.setData(n.getData());
                node.setRight(null);
                n.removeFromParent();
            } else {
                node.setData(n.getData());
                n.getParent().setRight(null);
                n.removeFromParent();
            }
        } else {
            BinaryTreeNode<E> n = node.getLeft() == null ? node.getRight() : node.getLeft();
            if (root == node) {
                setRoot(node);
            } else if (node.getParent().getLeft() == node) {
                node.getParent().setLeft(n);
            } else {
                node.getParent().setRight(n);
            }
        }
    }

    protected int compare(E x, E y) {
        if (comparator == null) {
            return ((Comparable) x).compareTo(y);
        } else {
            return comparator.compare(x, y);
        }
    }

    protected BinaryTreeNode getRoot() {
        return root;
    }

    protected void setRoot(BinaryTreeNode<E> node) {
        if (node != null) {
            node.removeFromParent();
        }
        root = node;
    }

    // find the rightest node of the left child of node
    protected BinaryTreeNode<E> predecessor(BinaryTreeNode<E> node) {
        BinaryTreeNode<E> n = node.getLeft();
        if (n != null) {
            while (n.getRight() != null) {
                n = n.getRight();
            }
        }
        return n;
    }

    protected BinaryTreeNode<E> nodeContaining(E data) {
        for (BinaryTreeNode<E> n = root; n != null; ) {
            int comparisonResult = compare(data, n.getData());
            if (comparisonResult == 0) {
                return n;
            } else if (comparisonResult < 0) {
                n = n.getLeft();
            } else {
                n = n.getRight();
            }
        }
        return null;
    }

    protected void rotateLeft(BinaryTreeNode<E> n) {
        if (n.getRight() == null) {
            return;
        }
        BinaryTreeNode<E> oldRight = n.getRight();
        n.setRight(oldRight.getLeft());
        if (n.getParent() == null) {
            //todo 不用考虑将oldRight.parent == null?
            root = oldRight;
        } else if (n.getParent().getLeft() == n) {
            n.getParent().setLeft(oldRight);
        } else {
            n.getParent().setRight(oldRight);
        }
        oldRight.setLeft(n);
    }

    protected void rotateRight(BinaryTreeNode<E> n) {
        if (n.getLeft() == null) {
            return;
        }
        BinaryTreeNode<E> oldLeft = n.getLeft();
        n.setLeft(oldLeft.getRight());
        if (n.getParent() == null) {
            root = oldLeft;
        } else if (n.getParent().getLeft() == n) {
            n.getParent().setLeft(oldLeft);
        } else {
            n.getParent().setRight(oldLeft);
        }
        oldLeft.setRight(n);
    }
}
