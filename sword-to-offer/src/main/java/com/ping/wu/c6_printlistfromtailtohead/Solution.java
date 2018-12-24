package com.ping.wu.c6_printlistfromtailtohead;

import java.util.ArrayList;

/**
 * @author wuping
 * @date 2018/12/21
 */

public class Solution {
    public static void main(String[] args) {

    }

    public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> ret = new ArrayList();
        if (listNode != null) {
            ret.addAll(printListFromTailToHead(listNode.next));
            ret.add(listNode.val);
        }
        return ret;
    }

    private static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}
