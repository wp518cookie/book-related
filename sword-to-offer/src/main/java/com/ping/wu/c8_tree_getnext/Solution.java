package com.ping.wu.c8_tree_getnext;

/**
 * @author wuping
 * @date 2018/12/27
 */

public class Solution {
    public static void main(String[] args) {
        TreeLinkNode node1 = new TreeLinkNode(1);
        TreeLinkNode node2 = new TreeLinkNode(2);
        TreeLinkNode node3 = new TreeLinkNode(3);
        TreeLinkNode node4 = new TreeLinkNode(4);
        TreeLinkNode node5 = new TreeLinkNode(5);
        node1.left = node2;
        node1.right = node3;
        node2.next = node1;
        node3.next = node1;

    }

    public static TreeLinkNode getNext(TreeLinkNode pNode) {
        if (pNode == null) {
            return pNode;
        }
        if (pNode.next == null) {
            TreeLinkNode t = pNode.right;
            if (t != null) {
                while (t.left != null) {
                    t = t.left;
                }
            }
            return t;
        }
        if (pNode.next.left == pNode) {
            TreeLinkNode t = pNode.right;
            if (t == null) {
                return pNode.next;
            } else {
                while (t.left != null) {
                    t = t.left;
                }
                return t;
            }
        } else {
            TreeLinkNode t = pNode.right;
            if (t != null) {
                while (t.left != null) {
                    t = t.left;
                }
                return t;
            } else {
                if (pNode.next.next != null) {
                    if (pNode.next.next.left == pNode.next) {
                        return pNode.next.next;
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }
            }
        }
    }

    private static class TreeLinkNode {
        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null;

        TreeLinkNode(int val) {
            this.val = val;
        }
    }
}
