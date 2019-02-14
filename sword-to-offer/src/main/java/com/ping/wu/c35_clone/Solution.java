package com.ping.wu.c35_clone;

/**
 * @author wuping
 * @date 2019/2/14
 */

public class Solution {
    public RandomListNode Clone(RandomListNode pHead) {
        if (pHead == null) {
            return null;
        }
        RandomListNode t = pHead;
        while (t != null) {
            RandomListNode temp = new RandomListNode(t.label);
            temp.next = t.next;
            t.next = temp;
            t = t.next.next;
        }
        t = pHead;
        while (t != null) {
            if (t.random != null) {
                t.next.random = t.random.next;
            }
            t = t.next.next;
        }
        RandomListNode dummy = new RandomListNode(-1);
        t = pHead;
        RandomListNode cloned = t.next;
        dummy.next = cloned;
        while (t != null) {
            t.next = cloned.next;
            if (t.next != null) {
                cloned.next = t.next.next;
            }
            t = t.next;
            cloned = cloned.next;
        }
        return dummy.next;
    }

    private static class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }
    }
}
