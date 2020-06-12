package com.ping.wu.chap15_dp;

/**
 * @author wuping
 * @date 2020-04-18
 */

public class RobCuttingRecursion {
    public static void main(String[] args) {
        int[] arr = new int[]{0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
        System.out.println(new RobCuttingRecursion().robCut(arr, 4));
    }

    public int robCut(int[] p, int n) {
        if (n == 0) {
            return 0;
        }
        int result = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            result = Math.max(p[i] + robCut(p, n - i), result);
        }
        return result;
    }
}
