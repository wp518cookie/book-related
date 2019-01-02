package com.ping.wu.c15_numberof1;

/**
 * @author wuping
 * @date 2018/12/27
 */

public class Solution {
    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(-1));
    }
    public static int NumberOf1(int n) {
        int result = 0;
        while (n != 0) {
            if ((n & 1) == 1) {
                result++;
            }
            n >>>= 1;
        }
        return result;
    }
}
