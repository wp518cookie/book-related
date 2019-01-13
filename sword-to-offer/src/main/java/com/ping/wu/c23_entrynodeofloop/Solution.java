package com.ping.wu.c23_entrynodeofloop;

import java.util.HashSet;
import java.util.Set;

/**
 * @author wuping
 * @date 2019/1/4
 * 一个链表中包含环，请找出该链表的环的入口结点。要求不能使用额外的空间
 */

public class Solution {
    public ListNode EntryNodeOfLoop1(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return null;
        }
        ListNode fast = pHead;
        ListNode slow = pHead;
        do {
            fast = fast.next.next;
            slow = slow.next;
        } while (fast != slow);
        fast = pHead;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    private static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}
