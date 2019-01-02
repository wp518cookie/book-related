package com.ping.wu.c21_reorderarray;

/**
 * @author wuping
 * @date 2018/12/27
 */

public class Solution {
    public static void reOrderArray(int[] array) {
        int[] temp = new int[array.length];
        int oddIdx = 0;
        int evenIdx = array.length - 1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 != 0) {
                temp[oddIdx++] = array[i];
            } else {
                temp[evenIdx--] = array[i];
            }
        }
        for (int i = 0; i < oddIdx; i++) {
            array[i] = temp[i];
        }
        for (int i = temp.length - 1; i > evenIdx; i--) {
            array[oddIdx++] = temp[i];
        }
    }

    public static void swap(int[] array, int oddIndex, int evenIndex) {
        int temp = array[oddIndex];
        array[oddIndex] = array[evenIndex];
        array[evenIndex] = temp;
    }
}
