package com.ping.wu.sorting.c2_2;

import org.apache.commons.lang3.RandomUtils;

import java.util.Arrays;

/**
 * @author wuping
 * @date 2020-06-02
 */

public class MergeEasy {
    public static void main(String[] args) {
        int[] nums = new int[100];
        for (int i = 0; i < 100; i++) {
            nums[i] = RandomUtils.nextInt(0, 300);
        }
        new MergeEasy().sort(nums);
        System.out.println(Arrays.toString(nums));
    }

    public void sort(int[] nums) {
        int[] aux = new int[nums.length];
        recursion(nums, aux, 0, nums.length - 1);
    }

    public void recursion(int[] nums, int[] aux, int low, int high) {
        if (low >= high) {
            return;
        }
        int mid = low + ((high - low) >> 1);
        recursion(nums, aux, low, mid);
        recursion(nums, aux, mid + 1, high);
        for (int i = low; i <= high; i++) {
            aux[i] = nums[i];
        }
        int i = low;
        int j = mid + 1;
        int t = low;
        while (i <= mid && j <= high) {
            if (aux[i] <= aux[j]) {
                nums[t++] = aux[i++];
            } else {
                nums[t++] = aux[j++];
            }
        }
        while (i <= mid) {
            nums[t++] = aux[i++];
        }
        while (j <= high) {
            nums[t++] = aux[j++];
        }
    }
}
