package com.ping.wu.c41_2_first_appearing_once;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author wuping
 * @date 2019/2/21
 * 请实现一个函数用来找出字符流中第一个只出现一次的字符。
 * 例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。
 * 当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"
 */

public class Solution {

    private int[] cnts = new int[256];
    private Queue<Character> queue = new LinkedList();

    public void Insert(char ch) {
        int idx = (int) ch;
        if (cnts[idx] == 0) {
            cnts[idx] = 1;
            queue.offer(ch);
        } else if (cnts[idx] == 1) {
            cnts[idx] = -1;
        } else {

        }
    }

    public char FirstAppearingOnce() {
        while (queue.size() > 0) {
            char t = queue.peek();
            int idx = (int) t;
            if (cnts[idx] == 1) {
                return t;
            } else {
                queue.poll();
            }
        }
        return '#';
    }
}
