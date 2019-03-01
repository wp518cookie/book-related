package com.ping.wu.c56_find_nums_appear_once;

/**
 * @author wuping
 * @date 2019/3/1
 */

public class Solution {
    public void FindNumsAppearOnce(int[] array, int num1[], int num2[]) {
        int diff = 0;
        for (int i = 0; i < array.length; i++) {
            diff ^= array[i];
        }
        int bitCount = 0;
        while (diff % 2 == 0) {
            diff = diff >> 1;
            bitCount++;
        }
        int bitMask = 1 << bitCount;
        num1[0] = 0;
        num2[0] = 0;
        for (int i = 0; i < array.length; i++) {
            if ((array[i] & bitMask) == 0) {
                num1[0] ^= array[i];
            } else {
                num2[0] ^= array[i];
            }
        }
    }
}
