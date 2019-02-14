package com.ping.wu.c27_mirror;


/**
 * @author wuping
 * @date 2019/2/12
 * 操作给定的二叉树，将其变换为源二叉树的镜像。
 */

public class Solution {
    public void Mirror(TreeNode root) {
        if (root == null) {
            return;
        }
        exchange(root);
        if (root.left != null) {
            Mirror(root.left);
        }
        if (root.right != null) {
            Mirror(root.right);
        }
    }

    public void exchange(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
    }

    private static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }
}
