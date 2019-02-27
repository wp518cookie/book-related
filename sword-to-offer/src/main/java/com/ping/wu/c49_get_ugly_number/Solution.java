package com.ping.wu.c49_get_ugly_number;

/**
 * @author wuping
 * @date 2019/2/27
 * todo 略难理解
 */

public class Solution {
    public static void main(String[] args) {
        GetUglyNumber_Solution(10);
    }

    public static int GetUglyNumber_Solution(int index) {
        if (index <= 6) {
            return index;
        }
        int i2 = 0, i3 = 0, i5 = 0;
        int[] dp = new int[index];
        dp[0] = 1;
        for (int i = 1; i < index; i++) {
            int next2 = dp[i2] * 2, next3 = dp[i3] * 3, next5 = dp[i5] * 5;
            dp[i] = Math.min(next2, Math.min(next3, next5));
            if (dp[i] == next2) {
                i2++;
            }
            if (dp[i] == next3) {
                i3++;
            }
            if (dp[i] == next5) {
                i5++;
            }
        }
        return dp[index - 1];
    }
}
