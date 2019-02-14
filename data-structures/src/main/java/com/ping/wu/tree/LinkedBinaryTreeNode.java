package com.ping.wu.tree;

/**
 * @author wuping
 * @date 2019/2/12
 */

public class LinkedBinaryTreeNode<E> implements BinaryTreeNode<E> {
    protected E data;
    protected LinkedBinaryTreeNode<E> parent;
    protected LinkedBinaryTreeNode<E> left;
    protected LinkedBinaryTreeNode<E> right;

    public LinkedBinaryTreeNode(E data) {
        this.data = data;
    }

    @Override
    public void setData(E data) {
        this.data = data;
    }

    @Override
    public E getData() {
        return data;
    }

    @Override
    public BinaryTreeNode<E> getParent() {
        return parent;
    }

    @Override
    public BinaryTreeNode<E> getLeft() {
        return left;
    }

    // ensure the child is not an ancestor
    @Override
    public void setLeft(BinaryTreeNode<E> child) {
        for (LinkedBinaryTreeNode<E> n = this; n != null; n = n.parent) {
            if (n == child) {
                throw new IllegalArgumentException();
            }
        }
        LinkedBinaryTreeNode<E> childNode = (LinkedBinaryTreeNode<E>) child;
        if (this.left != null) {
            this.left = null;
        }
        if (childNode != null) {
            childNode.removeFromParent();
            childNode.parent = this;
        }
        this.left = childNode;
    }

    @Override
    public BinaryTreeNode<E> getRight() {
        return right;
    }

    @Override
    public void setRight(BinaryTreeNode<E> child) {
        for (LinkedBinaryTreeNode<E> n = this; n != null; n = n.parent) {
            if (n == child) {
                throw new IllegalArgumentException();
            }
        }
        LinkedBinaryTreeNode<E> childNode = (LinkedBinaryTreeNode<E>) child;
        if (right != null) {
            right.parent = null;
        }
        if (childNode != null) {
            childNode.removeFromParent();
            childNode.parent = this;
        }
        this.right = childNode;
    }

    @Override
    public void removeFromParent() {
        if (parent != null) {
            if (parent.left == this) {
                parent.left = null;
            } else if (parent.right == this) {
                parent.right = null;
            }
            this.parent = null;
        }
    }

    @Override
    public void traversePreorder(BinaryTreeNode.Visitor visitor) {
        visitor.visit(this);
        if (left != null) {
            left.traversePreorder(visitor);
        }
        if (right != null) {
            right.traversePreorder(visitor);
        }
    }

    @Override
    public void traversePostorder(BinaryTreeNode.Visitor visitor) {
        if (left != null) {
            left.traversePostorder(visitor);
        }
        if (right != null) {
            right.traversePostorder(visitor);
        }
        visitor.visit(this);
    }

    @Override
    public void traverseInorder(BinaryTreeNode.Visitor visitor) {
        if (left != null) {
            left.traverseInorder(visitor);
        }
        visitor.visit(this);
        if (right != null) {
            right.traverseInorder(visitor);
        }
    }
}
