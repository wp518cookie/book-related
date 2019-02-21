package com.ping.wu.c39_morethan_half_num;

/**
 * @author wuping
 * @date 2018/12/26     Moore majority vote algorithm(摩尔投票算法)
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * 例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。由于数字2在数组中出现了5次，
 * 超过数组长度的一半，因此输出2。如果不存在则输出0。
 */

public class Solution {
    public static void main(String[] args) {
        System.out.println(MoreThanHalfNum_Solution(new int[]{1,2,2,2,4}));
    }
    public static int MoreThanHalfNum_Solution(int[] nums) {
        int count = 0;
        int m = -1;
        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                m = nums[i];
                count++;
            } else if (m == nums[i]) {
                count++;
            } else {
                count--;
            }
        }
        return m;
    }
}
