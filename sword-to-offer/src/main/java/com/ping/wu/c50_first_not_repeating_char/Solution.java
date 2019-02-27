package com.ping.wu.c50_first_not_repeating_char;

/**
 * @author wuping
 * @date 2019/2/27
 */

public class Solution {
    public int FirstNotRepeatingChar(String str) {
        if (str == null || str.length() == 0) {
            return -1;
        }
        if (str.length() == 1) {
            return 0;
        }
        int[] arr = new int[128];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = -1;
        }
        for (int i = 0; i < str.length(); i++) {
            int idx = str.charAt(i);
            if (arr[idx] == -1) {
                arr[idx] = i;
            } else {
                arr[idx] = -2;
            }
        }
        int min = str.length();
        for (int t : arr) {
            if (t >= 0 && min > t) {
                min = t;
            }
        }
        return min;
    }
}
