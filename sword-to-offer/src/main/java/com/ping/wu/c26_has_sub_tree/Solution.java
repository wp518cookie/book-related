package com.ping.wu.c26_has_sub_tree;

/**
 * @author wuping
 * @date 2019/2/12
 * 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
 */

public class Solution {
    public static boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) {
            return false;
        }
        return isSubTree(root1, root2) || isSubTree(root1.left, root2) || isSubTree(root1.right, root2);
    }

    public static boolean isSubTree(TreeNode t1, TreeNode t2) {
        if (t2 == null) {
            return true;
        }
        if (t1 == null) {
            return false;
        }
        if (t1.val != t2.val) {
            return false;
        }
        return isSubTree(t1.left, t2.left) && isSubTree(t1.right, t2.right);
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
