package com.ping.wu.c54_kth_node;

/**
 * @author wuping
 * @date 2019/2/28
 */



public class Solution {
    private static int t = 0;

    public static void main(String[] args) {
        TreeNode l5 = new TreeNode(5);
        TreeNode l3 = new TreeNode(3);
        TreeNode l2 = new TreeNode(2);
        TreeNode l4 = new TreeNode(4);
        TreeNode l7 = new TreeNode(7);
        TreeNode l6 = new TreeNode(6);
        TreeNode l8 = new TreeNode(8);
        l5.left = l3;
        l5.right = l7;
        l3.left = l2;
        l3.right = l4;
        l7.left = l6;
        l7.right = l8;
        TreeNode result = KthNode(l5, 3);
        System.out.println(result.val);
    }
    static TreeNode KthNode(TreeNode pRoot, int k) {
        if (pRoot == null) {
            return null;
        }
        TreeNode result = KthNode(pRoot.left, k);
        if (result != null) {
            return result;
        }
        t++;
        if (t == k) {
            return pRoot;
        }
        return KthNode(pRoot.right, k);
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
