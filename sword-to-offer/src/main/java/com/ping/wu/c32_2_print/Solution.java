package com.ping.wu.c32_2_print;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author wuping
 * @date 2019/2/14
 */

public class Solution {
    ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (pRoot == null) {
            return new ArrayList();
        }
        queue.offer(pRoot);
        ArrayList<ArrayList<Integer>> result = new ArrayList();
        int size = 1;
        while (size > 0) {
            ArrayList<Integer> meta = new ArrayList();
            while (size > 0) {
                TreeNode t = queue.poll();
                size--;
                meta.add(t.val);
                if (t.left != null) {
                    queue.offer(t.left);
                }
                if (t.right != null) {
                    queue.offer(t.right);
                }
            }
            result.add(meta);
            size = queue.size();
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
