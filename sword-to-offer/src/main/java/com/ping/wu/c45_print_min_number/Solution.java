package com.ping.wu.c45_print_min_number;

import java.util.Arrays;

/**
 * @author wuping
 * @date 2019/2/22
 */

public class Solution {
    public String PrintMinNumber(int [] numbers) {
        if (numbers == null || numbers.length == 0) {
            return "";
        }
        int n = numbers.length;
        String[] nums = new String[n];
        for (int i = 0; i < n; i++) {
            nums[i] = numbers[i] + "";
        }
        Arrays.sort(nums, (s1, s2) -> (s1 + s2).compareTo(s2 + s1));
        StringBuilder sb = new StringBuilder();
        for (String s : nums) {
            sb.append(s);
        }
        return sb.toString();
    }
}
