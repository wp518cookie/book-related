package com.ping.wu.c55_2_is_balanced_solution;

/**
 * @author wuping
 * @date 2019/3/1
 */

public class Solution {
    private static boolean isBalanced = true;

    public static boolean IsBalanced_Solution(TreeNode root) {
        depth(root);
        return isBalanced;
    }

    public static int depth(TreeNode root) {
        if (root == null || !isBalanced) {
            return 0;
        }
        if (Math.abs(depth(root.left) - depth(root.right)) > 1) {
            isBalanced = false;
        }
        return 1 + Math.max(depth(root.left), depth(root.right));
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
