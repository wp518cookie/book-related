package com.ping.wu.sorting.c2_1;

import org.apache.commons.lang3.RandomUtils;

import java.util.Arrays;

/**
 * @author wuping
 * @date 2020-06-02
 *
 * 对输入数据的顺序不敏感
 *
 */

public class SelectionEasy {
    public static void main(String[] args) {
        int[] nums = new int[100];
        for (int i = 0; i < 100; i++) {
            nums[i] = RandomUtils.nextInt(0, 300);
        }
        new SelectionEasy().sort(nums);
        System.out.println(Arrays.toString(nums));
    }
    public void sort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int min = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[min]) {
                    min = j;
                }
            }
            exch(nums, i, min);
        }
    }

    public void exch(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
