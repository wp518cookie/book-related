package com.ping.wu.c14_integer_break;

/**
 * @author wuping
 * @date 2019/1/2
 * 把一根绳子剪成多段，并且使得每段的长度乘积最大。
 * Input: 10
 * Output: 36
 * Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.
 */

public class Solution {
    public static void main(String[] args) {
        System.out.println(integerBreak(10));
    }

    public static int integerBreak(int n) {
        if (n < 4) {
            return n - 1;
        }
        if (n == 4) {
            return n;
        }
        int result = 1;
        while (n >= 5) {
            result *= 3;
            n = n - 3;
        }
        return result * n;
    }
}
