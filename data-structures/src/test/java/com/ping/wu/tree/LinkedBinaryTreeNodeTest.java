package com.ping.wu.tree;

import sun.java2d.SunGraphics2D;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.VolatileImage;
import java.text.AttributedCharacterIterator;

/**
 * @author wuping
 * @date 2019/2/13
 */

public class LinkedBinaryTreeNodeTest {
    public static void main(String[] args) {
        LinkedBinaryTreeNode<Integer> root = new LinkedBinaryTreeNode<>(3);
        LinkedBinaryTreeNode<Integer> t1 = new LinkedBinaryTreeNode<>(1);
        LinkedBinaryTreeNode<Integer> t2 = new LinkedBinaryTreeNode<>(2);
        LinkedBinaryTreeNode<Integer> t3 = new LinkedBinaryTreeNode<>(4);
        LinkedBinaryTreeNode<Integer> t4 = new LinkedBinaryTreeNode<>(5);
        root.left = t1;
        t1.parent = root;
        root.right = t3;
        t3.parent = root;
        t1.right = t2;
        t2.parent = t1;
        t3.right = t4;
        t4.parent = t3;
        BinaryTreePanel t = new BinaryTreePanel(root, 100, 100);
        final GraphicsEnvironment ge =
                GraphicsEnvironment.getLocalGraphicsEnvironment();
        final GraphicsConfiguration gc =
                ge.getDefaultScreenDevice().getDefaultConfiguration();
        final VolatileImage vi = gc.createCompatibleVolatileImage(200, 200);
        final SunGraphics2D sg2d = (SunGraphics2D) vi.createGraphics();
        t.paintComponent(sg2d);
    }
}
