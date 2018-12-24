package com.ping.wu.c3_duplicate_num;

/**
 * @author wuping
 * @date 2018/12/15
 */

public class DuplicateNum {
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,0};
        System.out.println(duplicate(arr, 4, new int[1]));
    }

    public static boolean duplicate(int[] nums, int length, int[] duplication) {
        if (nums == null || length <= 0) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    duplication[0] = nums[i];
                    return true;
                }
                swap(nums, nums[i], i);
            }
        }
        return false;
    }

    public static void swap(int[] nums, int i, int j) {
        nums[i] = nums[i] + nums[j];
        nums[j] = nums[i] - nums[j];
        nums[i] = nums[i] - nums[j];
    }
}
