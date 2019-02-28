package com.ping.wu.c53_get_number_of_k;

/**
 * @author wuping
 * @date 2019/2/28
 */

public class Solution {
    public int GetNumberOfK(int[] array, int k) {
        return 0;
    }

    private int binarySearch(int[] nums, int K) {
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start) >> 1;
            if (nums[mid] >= K) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }
}
