package com.ping.wu.algo.sort;

import java.util.Arrays;

/**
 * @author wuping
 * @date 2019-07-24
 */

public class MergeSort implements Sortable {
    public static void main(String[] args) {
        NumberUtils.sort(NumberUtils.getRandomArs(20, 100), new MergeSort());
    }

    @Override
    public void sort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        int[] tmp = new int[nums.length];
        sort(nums, tmp, 0, nums.length - 1);
    }

    private void sort(int[] nums, int[] temp, int start, int end) {
        if (start >= end) {
            return;
        }
        int pivot = start + ((end - start) >> 1);
        sort(nums, temp, 0, pivot);
        sort(nums, temp, pivot + 1, end);
        merge(nums,temp, start, pivot, end);
    }

    private void merge(int[] nums, int[] temp, int start, int pivot, int end) {
        int i = start;
        int j = pivot + 1;
        int t = start;
        while (i <= pivot && j <= end) {
            if (nums[i] > nums[j]) {
                temp[t++] = nums[j++];
            } else {
                temp[t++] = nums[i++];
            }
        }
        while (i <= pivot) {
            temp[t++] = nums[i++];
        }
        while (j <= end) {
            temp[t++] = nums[j++];
        }
        for (int k = start; k <= end; k++) {
            nums[k] = temp[k];
        }
    }

    @Override
    public String getSortName() {
        return "merge sort";
    }
}
