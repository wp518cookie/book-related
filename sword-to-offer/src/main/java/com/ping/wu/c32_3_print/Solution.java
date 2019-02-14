package com.ping.wu.c32_3_print;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author wuping
 * @date 2019/2/14
 * 请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，
 * 第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。
 */

public class Solution {
    public ArrayList<ArrayList<Integer>> print(TreeNode pRoot) {
        if (pRoot == null) {
            return new ArrayList();
        }
        LinkedList<TreeNode> linkedList = new LinkedList();
        ArrayList<ArrayList<Integer>> result = new ArrayList();
        linkedList.add(pRoot);
        int size = 1;
        boolean flag = true;
        while (size > 0) {
            LinkedList<TreeNode> bak = new LinkedList();
            ArrayList<Integer> meta = new ArrayList();
            while (size > 0) {
                TreeNode t;
                if (flag) {
                    t = linkedList.removeFirst();
                    if (t.left != null) {
                        bak.add(t.left);
                    }
                    if (t.right != null) {
                        bak.add(t.right);
                    }
                } else {
                    t = linkedList.removeLast();
                    if (t.right != null) {
                        bak.addFirst(t.right);
                    }
                    if (t.left != null) {
                        bak.addFirst(t.left);
                    }
                }
                meta.add(t.val);
                size--;
            }
            result.add(meta);
            flag = !flag;
            linkedList = bak;
            size = linkedList.size();
        }
        return result;
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
