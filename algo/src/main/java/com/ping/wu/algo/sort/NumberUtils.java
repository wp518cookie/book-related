package com.ping.wu.algo.sort;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by Administrator on 2018/4/2.
 */
public class NumberUtils {
    public static int[] getRandomArs(int length, int max) {
        int[] rs = new int[length];
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            rs[i] = random.nextInt(max);
        }
        return rs;
    }

    public static void sort(int[] nums, Sortable sortable) {
        System.out.println("排序前:" + Arrays.toString(nums));
        Set<Integer> set = new HashSet();
        for (int t : nums) {
            set.add(t);
        }
        sortable.sort(nums);
        System.out.println("经过" + sortable.getSortName() + "排序后:" + Arrays.toString(nums));
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                System.out.println("第" + i + "," + (i - 1) + "个排序有错误！");
                return;
            }
        }
        for (int t : nums) {
            if (!set.contains(t)) {
                System.out.println("排序后元素:" + t + "丢失了！");
            }
        }
        System.out.println("排序正确");
    }
}
