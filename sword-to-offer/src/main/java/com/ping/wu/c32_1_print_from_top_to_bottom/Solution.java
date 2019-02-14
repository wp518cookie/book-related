package com.ping.wu.c32_1_print_from_top_to_bottom;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author wuping
 * @date 2019/2/14
 */

public class Solution {
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList();
        if (root == null) {
            return new ArrayList();
        }
        ArrayList<Integer> result = new ArrayList();
        queue.offer(root);
        int size = 1;
        while (size > 0) {
            while (size > 0) {
                TreeNode t = queue.poll();
                result.add(t.val);
                size--;
                if (t.left != null) {
                    queue.offer(t.left);
                }
                if (t.right != null) {
                    queue.offer(t.right);
                }
            }
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
