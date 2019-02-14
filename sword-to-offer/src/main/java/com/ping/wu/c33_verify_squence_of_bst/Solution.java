package com.ping.wu.c33_verify_squence_of_bst;

/**
 * @author wuping
 * @date 2019/2/14
 */

public class Solution {
    public boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence == null || sequence.length <= 3) {
            return true;
        }
        return judge(sequence, 0, sequence.length - 1);
    }

    public boolean judge(int[] sequence, int start, int end) {
        if (start >= end) {
            return true;
        }
        int root = sequence[end];
        int i = start;
        while (i < end) {
            if (sequence[i] < root) {
                i++;
            } else {
                break;
            }
        }
        for (int j = i; j < end; j++) {
            if (sequence[j] < root) {
                return false;
            }
        }
        if (i == end) {
            return judge(sequence, start, end - 1);
        } else {
            return judge(sequence, start, i - 1) && judge(sequence, i, end - 1);
        }
    }
}
