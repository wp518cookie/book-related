package com.ping.wu.c58_2_left_rotate_string;

/**
 * @author wuping
 * @date 2019/3/1
 */

public class Solution {
    public String LeftRotateString(String str,int n) {
        char[] arr = new char[str.length()];
        int idx = 0;
        for (int i = 0; i < str.length(); i++) {
            if (n > 0) {
                arr[str.length() - n] = str.charAt(i);
                n--;
            } else {
                arr[idx] = str.charAt(i);
                idx++;
            }
        }
        return new String(arr);
    }
}
