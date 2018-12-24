package com.ping.wu.c10_1_fibonacci;

/**
 * @author wuping
 * @date 2018/12/23
 */

public class Solution {
    public int Fibonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int pre2 = 1;
        int pre1 = 0;
        for (int i = 2; i <= n; i++) {
            int t = pre2;
            pre2 = pre1 + pre2;
            pre1 = t;
        }
        return pre2;
    }
}
