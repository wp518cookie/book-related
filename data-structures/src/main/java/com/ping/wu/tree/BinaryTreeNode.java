package com.ping.wu.tree;

/**
 * @author wuping
 * @date 2019/2/12
 */

public interface BinaryTreeNode<E> {
    E getData();

    void setData(E data);

    BinaryTreeNode<E> getParent();

    BinaryTreeNode<E> getLeft();

    void setLeft(BinaryTreeNode<E> child);

    BinaryTreeNode<E> getRight();

    void setRight(BinaryTreeNode<E> child);

    void removeFromParent();

    void traversePreorder(Visitor visitor);

    void traversePostorder(Visitor visitor);

    void traverseInorder(Visitor visitor);

    public interface Visitor {
        <E> void visit(BinaryTreeNode<E> node);
    }
}
