package com.ping.wu.c37_serialize;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author wuping
 * @date 2019/2/15
 * 请实现两个函数，分别用来序列化和反序列化二叉树
 */

public class Solution {
    private static String NULL_IDENTIFY = "#";
    private static TreeNode NULL_NODE = new TreeNode(-1);

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        TreeNode treeNode1 = new TreeNode(2);
        TreeNode treeNode2 = new TreeNode(3);
        treeNode.left = treeNode1;
        treeNode.right = treeNode2;
        String result = Serialize(treeNode);
        TreeNode t = Deserialize(result);
        System.out.println(123);
    }

    public static String Serialize(TreeNode root) {
        if (root == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList();
        queue.offer(root);
        int size = 1;
        boolean needContinue = true;
        while (size > 0) {
            needContinue = false;
            while (size > 0) {
                TreeNode t = queue.poll();
                if (t == NULL_NODE) {
                    sb.append(NULL_IDENTIFY).append(",");
                    queue.offer(NULL_NODE);
                    queue.offer(NULL_NODE);
                } else {
                    sb.append(t.val).append(",");
                    if (t.left != null) {
                        queue.offer(t.left);
                        needContinue = true;
                    } else {
                        queue.offer(NULL_NODE);
                    }
                    if (t.right != null) {
                        queue.offer(t.right);
                        needContinue = true;
                    } else {
                        queue.offer(NULL_NODE);
                    }
                }
                size--;
            }
            if (!needContinue) {
                break;
            }
            size = queue.size();
        }
        String result = sb.toString();
        return result.substring(0, result.length() - 1);
    }

    public static TreeNode Deserialize(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        String[] arr = str.split(",");
        TreeNode root = new TreeNode(Integer.valueOf(arr[0]));
        Queue<TreeNode> queue = new LinkedList();
        queue.offer(root);
        for (int i = 1; i < arr.length; ) {
            int size = queue.size();
            while (size > 0) {
                TreeNode t = queue.poll();
                if (t == NULL_NODE) {
                    queue.offer(NULL_NODE);
                    queue.offer(NULL_NODE);
                    i++;
                    i++;
                } else {
                    if (arr[i].equals("#")) {
                        t.left = null;
                        i++;
                        queue.offer(NULL_NODE);
                    } else {
                        t.left = new TreeNode(Integer.valueOf(arr[i]));
                        i++;
                        queue.offer(t.left);
                    }
                    if (arr[i].equals("#")) {
                        t.right = null;
                        i++;
                        queue.offer(NULL_NODE);
                    } else {
                        t.right = new TreeNode(Integer.valueOf(arr[i]));
                        i++;
                        queue.offer(t.right);
                    }
                }
                size--;
            }
        }
        return root;
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
