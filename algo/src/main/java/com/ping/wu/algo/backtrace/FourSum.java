//package com.ping.wu.algo.backtrace;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
///**
// * @author wuping
// * @date 2019/2/20
// */
//
//public class FourSum {
//    private Set<Integer> sets = new HashSet();
//
//    public List<List<Integer>> fourSum(int[] nums, int target) {
//        List<List<Integer>> result = new ArrayList();
//        List<Integer> list = new ArrayList();
//        Arrays.sort(nums);
//        backTracing(list, nums, 4, target, result);
//        return result;
//    }
//
//    public void backTracing(List<Integer> list, int[] nums, int start, int size, int target, List<List<Integer>> result) {
//        int max = nums[nums.length - 1];
//        int k = size - list.size();
//        if (max * k < target || nums[start] * k > target) {
//            return;
//        }
//        if (k == 2) {
//            int i = start;
//            int j = nums.length - 1;
//            while (i < j) {
//                int sum = nums[i] + nums[j];
//                if (sum < target) {
//                    i++;
//                    continue;
//                } else if (sum > target) {
//                    j--;
//                    continue;
//                } else {
//                    List<Integer> temp = new ArrayList(list);
//                    temp.add(nums[i]);
//                    temp.add(nums[j]);
//                    result.add(temp);
//                }
//                while (++i < j && nums[i] == nums[i - 1]) {
//
//                }
//                while ()
//            }
//        }
//        for (int i = start; i < nums.length; i++) {
//            if ()
//                backTracing();
//        }
//    }
//}
