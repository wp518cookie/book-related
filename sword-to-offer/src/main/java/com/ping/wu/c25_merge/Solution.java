package com.ping.wu.c25_merge;

/**
 * @author wuping
 * @date 2019/1/9
 * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
 */

public class Solution {
    public ListNode Merge(ListNode list1,ListNode list2) {
        if (list1 == null || list2 == null) {
            return list1 == null ? list2 : list1;
        }
        ListNode dummy = new ListNode(-1);
        ListNode cur = null;
        while (list1 != null && list2 != null) {
            if (list1.val > list2.val) {
                if (cur == null) {
                    dummy.next = list2;
                    cur = list2;
                } else {
                    cur.next = list2;
                    cur = list2;
                }
                list2 = list2.next;
            } else {
                if (cur == null) {
                    dummy.next = list1;
                    cur = list1;
                } else {
                    cur.next = list1;
                    cur = list1;
                }
                list1 = list1.next;
            }
        }
        if (list1 == null) {
            cur.next = list2;
        } else {
            cur.next = list1;
        }
        return dummy.next;
    }

    private static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}
