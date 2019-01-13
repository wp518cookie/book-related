package com.ping.wu.c24_reverselist;

/**
 * @author wuping
 * @date 2019/1/9
 * 输入一个链表，反转链表后，输出新链表的表头。
 */

public class Solution {
    public ListNode ReverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        ListNode cursor = head;
        while (cursor != null) {
            ListNode first = dummy.next;
            dummy.next = cursor;
            ListNode next = cursor.next;
            cursor.next = first;
            cursor = next;
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
