package com.ping.wu.algo.sort;

import java.util.Arrays;

/**
 * @author wuping
 * @date 2019-07-24
 */

public class QuickSort implements Sortable {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            NumberUtils.sort(NumberUtils.getRandomArs(500, 2000), new QuickSort());
        }
    }

    @Override
    public void sort(int[] nums) {
        sort(nums, 0, nums.length - 1);
    }

    public void sort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        if (start + 1 == end) {
            if (nums[start] > nums[end]) {
                swap(nums, start, end);
                return;
            } else {
                return;
            }
        }
        int val = getPivot(nums, start, end);
        int i = start;
        int j = end;
        while (i < j) {
            while (i < j && nums[i] <= val) {
                i++;
            }
            while (i < j && nums[j] >= val) {
                j--;
            }
            if (i < j) {
                swap(nums, i, j);
            }
        }
        swap(nums, i, end);
        sort(nums, start, i - 1);
        sort(nums, i + 1, end);
    }

    public int getPivot(int[] nums, int start, int end) {
        int mid = start + ((end - start) >> 1);
        int temp = mid;
        if (nums[start] >= nums[mid]) {
            if (nums[end] <= nums[mid]) {
                temp = mid;
            } else {
                temp = nums[start] >= nums[end] ? end : start;
            }
        } else {
            if (nums[mid] <= nums[end]) {
                temp = mid;
            } else {
                temp = nums[start] >= nums[end] ? start : end;
            }
        }
        swap(nums, temp, end);
        return nums[end];
    }

    @Override
    public String getSortName() {
        return "quick sort";
    }
}
