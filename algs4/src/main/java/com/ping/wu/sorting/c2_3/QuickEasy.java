package com.ping.wu.sorting.c2_3;

import org.apache.commons.lang3.RandomUtils;

import java.util.Arrays;

/**
 * @author wuping
 * @date 2020-06-03
 */

public class QuickEasy {
    public static void main(String[] args) {
        int[] nums = new int[100];
        for (int i = 0; i < 100; i++) {
            nums[i] = RandomUtils.nextInt(0, 300);
        }
        new QuickEasy().sort(nums);
        System.out.println(Arrays.toString(nums));
        new QuickEasy().check(nums);
    }

    public void sort(int[] nums) {
        recursion(nums, 0, nums.length - 1);
    }

    public void recursion(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int point = nums[start];
        nums[start] = nums[end];
        int i = start;
        int j = end - 1;
        while (true) {
            while (i < end && nums[i] <= point) {
                i++;
            }
            while (j >= 0 && nums[j] > point) {
                j--;
            }
            if (i < j) {
                exchan(nums, i, j);
                i++;
                j--;
            } else {
                break;
            }
        }
        nums[end] = nums[i];
        nums[i] = point;
        recursion(nums, start, i - 1);
        recursion(nums, i + 1, end);
    }

    public void exchan(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void check(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                System.out.println("顺序不对");
                break;
            }
        }
    }
}
