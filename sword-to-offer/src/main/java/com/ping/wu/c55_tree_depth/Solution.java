package com.ping.wu.c55_tree_depth;

/**
 * @author wuping
 * @date 2019/2/28
 */


public class Solution {
    public static void main(String[] args) {

    }

    public static int TreeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(TreeDepth(root.left), TreeDepth(root.right));
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
