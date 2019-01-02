package com.ping.wu.c39_morethan_half_num;

/**
 * @author wuping
 * @date 2018/12/26
 */

public class Solution {
    public static void main(String[] args) {
        System.out.println(MoreThanHalfNum_Solution(new int[]{1,2,2,2,4}));
    }
    public static int MoreThanHalfNum_Solution(int [] nums) {
        int majority = nums[0];
        for (int i = 1, cnt = 1; i < nums.length; i++) {
            cnt = nums[i] == majority ? cnt + 1 : cnt - 1;
            if (cnt == 0) {
                majority = nums[i];
                cnt = 1;
            }
        }
        int cnt = 0;
        for (int val : nums)
            if (val == majority)
                cnt++;
        return cnt > nums.length / 2 ? majority : 0;
    }
}
