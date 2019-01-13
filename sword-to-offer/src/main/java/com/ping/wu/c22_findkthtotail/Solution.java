package com.ping.wu.c22_findkthtotail;

/**
 * @author wuping
 * @date 2019/1/4
 * 输入一个链表，输出该链表中倒数第k个结点。
 */

public class Solution {
    public ListNode FindKthToTail(ListNode head,int k) {
        if (head == null || k <= 0) {
            return null;
        }
        ListNode cursor1 = head;
        ListNode cursor2 = head;
        int c = 0;
        while (c < k - 1) {
            if (cursor1 == null) {
                return null;
            }
            cursor1 = cursor1.next;
            c++;
        }
        if (cursor1 == null) {
            return null;
        }
        while (cursor1.next != null) {
            cursor1 = cursor1.next;
            cursor2 = cursor2.next;
        }
        return cursor2;
    }
//    public ListNode FindKthToTail(ListNode head,int k) {
//        if (head == null) {
//            return null;
//        }
//        int len = 1;
//        ListNode cursor = head;
//        while((cursor = cursor.next) != null) {
//            len++;
//        }
//        if (k > len) {
//            return null;
//        }
//        int count = len - k;
//        cursor = head;
//        while (count-- > 0) {
//            cursor = cursor.next;
//        }
//        return cursor;
//    }

    private static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}
