package com.ping.wu.sorting.c2_1;

import org.apache.commons.lang3.RandomUtils;

import java.util.Arrays;

/**
 * @author wuping
 * @date 2020-06-02
 */

public class ShellEasy {
    public static void main(String[] args) {
        int[] nums = new int[100];
        for (int i = 0; i < 100; i++) {
            nums[i] = RandomUtils.nextInt(0, 300);
        }
        new ShellEasy().sort(nums);
        System.out.println(Arrays.toString(nums));
    }

    public void sort(int[] nums) {
        int N = nums.length;
        int h = 1;
        while (h < N / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && nums[j] < nums[j - h]; j -= h) {
                    exch(nums, j, j - h);
                }
            }
            h = h / 3;
        }
    }

    private void exch(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
