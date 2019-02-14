package com.ping.wu.tree;

import java.awt.*;
import java.util.Comparator;

/**
 * @author wuping
 * @date 2019/2/13
 */

public class RedBlackTree extends BinarySearchTree {
    public RedBlackTree() {
        this(null);
    }

    public RedBlackTree(Comparator c) {
        super(c);
    }

    @Override
    public void add(Object data) {
        if (root == null) {
            root = new Node(data);
        }
        BinaryTreeNode n = root;
        while (true) {
            int comparisonResult = compare(data, n.getData());
            if (comparisonResult == 0) {
                n.setData(data);
                return;
            } else if (comparisonResult < 0) {
                if (n.getLeft() == null) {
                    n.setLeft(new Node(data));
                    adjustAfterInsertion((Node) n.getLeft());
                    break;
                }
                n = n.getLeft();
            } else {
                if (n.getRight() == null) {
                    n.setRight(new Node(data));
                    adjustAfterInsertion((Node) n.getRight());
                    break;
                }
                n = n.getRight();
            }
        }
    }

    @Override
    public void remove(Object data) {
        Node node = (Node) nodeContaining(data);
        if (node == null) {
            return;
            // 左右子树都不为空，用前驱节点替换
        } else if (node.getLeft() != null && node.getRight() != null) {
            BinaryTreeNode predecessor = predecessor(node);
            node.setData(predecessor.getData());
            node = (Node) predecessor;
        }
        // 提名
        Node pullUp = leftOf(node) == null ? rightOf(node) : leftOf(node);
        // 左右子树有一个不为空
        if (pullUp != null) {
            if (node == root) {
                setRoot(pullUp);
            } else if (node.getParent().getLeft() == node) {
                node.getParent().setLeft(pullUp);
            } else {
                node.getParent().setRight(pullUp);
            }
            // 该节点少一个黑色节点
            if (isBlack(node)) {
                adjustAfterRemoval(pullUp);
            }
            // 左右节点为空，且是根节点
        } else if (node == root) {
            setRoot(null);
        } else {
            // 左右节点都是空
            if (isBlack(node)) {
                adjustAfterRemoval(node);
            }
            node.removeFromParent();
        }
    }

    private void adjustAfterRemoval(Node n) {
        while (n != root && isBlack(n)) {
            if (n == leftOf(parentOf(n))) {
                Node sibling = rightOf(parentOf(n));

            }
        }
    }

    private void adjustAfterInsertion(Node n) {
        // step1: color the node red
        setColor(n, Color.RED);
        // step2: if double red, correct
        if (n != null && n != null && isRed(parentOf(n))) {
            //step2a simplest: parent and its sibling is all red
            if (isRed(siblingOf(parentOf(n)))) {
                setColor(parentOf(n), Color.BLACK);
                setColor(siblingOf(parentOf(n)), Color.BLACK);
                setColor(grandparentOf(n), Color.RED);
                adjustAfterInsertion(grandparentOf(n));
            } else if (parentOf(n) == leftOf(grandparentOf(n))) {
                if (n == rightOf(parentOf(n))) {
                    rotateLeft(n = parentOf(n));
                }
                setColor(parentOf(n), Color.BLACK);
                setColor(grandparentOf(n), Color.RED);
                rotateRight(grandparentOf(n));
            } else if (parentOf(n) == rightOf(grandparentOf(n))) {
                if (n == leftOf(parentOf(n))) {
                    rotateRight(n = parentOf(n));
                }
                setColor(parentOf(n), Color.BLACK);
                setColor(grandparentOf(n), Color.RED);
                rotateLeft(grandparentOf(n));
            }
            setColor((Node) root, Color.BLACK);
        }
    }

    private Color colorOf(Node n) {
        return n == null ? Color.BLACK : n.color;
    }

    private boolean isRed(Node n) {
        return n.color != null && n.color == Color.RED;
    }

    private boolean isBlack(Node n) {
        return n.color == null || n.color == Color.BLACK;
    }

    private void setColor(Node n, Color c) {
        if (n != null) {
            n.color = c;
        }
    }

    private Node parentOf(Node n) {
        return n == null ? null : (Node) n.getParent();
    }

    private Node grandparentOf(Node n) {
        return (n == null || n.getParent() == null) ? null : (Node) n.getParent().getParent();
    }

    private Node siblingOf(Node n) {
        return (n == null || n.getParent() == null) ? null :
                (n.getParent().getLeft() == n ? (Node) n.getParent().getRight() : (Node) n.getParent().getLeft());
    }

    private Node leftOf(Node n) {
        return n == null ? null : (Node) n.getLeft();
    }

    private Node rightOf(Node n) {
        return n == null ? null : (Node) n.getRight();
    }

    private static class Node extends LinkedBinaryTreeNode {
        Color color = Color.BLACK;

        public Node(Object data) {
            super(data);
        }
    }
}
