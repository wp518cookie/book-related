package com.ping.wu.sorting.c2_1;

import org.apache.commons.lang3.RandomUtils;

import java.util.Arrays;

/**
 * @author wuping
 * @date 2020-06-02
 *
 * 对输入数据敏感，若是数据接近有序则很快
 */

public class InsertionEasy {
    public static void main(String[] args) {
        int[] nums = new int[100];
        for (int i = 0; i < 100; i++) {
            nums[i] = RandomUtils.nextInt(0, 300);
        }
        new InsertionEasy().sort(nums);
        System.out.println(Arrays.toString(nums));
    }

    public void sort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            for (int j = i; j > 0 && nums[j] < nums[j - 1]; j--) {
                exch(nums, j, j - 1);
            }
        }
    }

    public void exch(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
