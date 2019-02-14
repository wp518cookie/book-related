package com.ping.wu.c34_find_path;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wuping
 * @date 2019/2/14
 * 输入一颗二叉树的跟节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
 * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。(注意: 在返回值的list中，数组长度大的数组靠前)
 */

public class Solution {
    private ArrayList<ArrayList<Integer>> result = new ArrayList();
    private List<Integer> list = new ArrayList();

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        if (root == null) {
            return new ArrayList();
        }
        list.add(root.val);
        int gap = target - root.val;
        if (gap == 0 && root.left == null && root.right == null) {

        }
        return null;
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
