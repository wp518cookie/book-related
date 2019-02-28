package com.ping.wu.c52_find_first_common_node;

/**
 * @author wuping
 * @date 2019/2/27
 */

public class Solution {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        l1.next = l2;
        l2.next = l4;
        l3.next = l4;
        l4.next = l5;
        ListNode result = FindFirstCommonNode(l1, l3);
        System.out.println(result.val);
    }

    public static ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
//        if (pHead1 == null || pHead2 == null) {
//            return null;
//        }
//        ListNode p1 = pHead1;
//        ListNode p2 = pHead2;
//        while(p1 != p2) {
//            p1 = p1.next;
//            p2 = p2.next;
//            if (p1 == null) {
//                p1 = pHead2;
//            }
//            if (p2 == null) {
//                p2 = pHead1;
//            }
//        }
//        return p1;
        ListNode l1 = pHead1, l2 = pHead2;
        while (l1 != l2) {
            l1 = (l1 == null) ? pHead2 : l1.next;
            l2 = (l2 == null) ? pHead1 : l2.next;
        }
        return l1;
    }

    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}
