package com.ping.wu.c57_1_find_numbers_with_sum;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wuping
 * @date 2019/3/1
 * 输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的。
 */

public class Solution {
    public ArrayList<Integer> FindNumbersWithSum(int [] array, int sum) {
        if (array == null || array.length <= 1 || array[0] * 2 > sum || array[array.length - 1] * 2 < sum) {
            return new ArrayList();
        }
        ArrayList<Integer> result = new ArrayList<>();
        int start = 0;
        int end = array.length - 1;
        while (start < end) {
            int temp = array[start] + array[end];
            if (temp == sum) {
                if (result.size() == 0) {
                    result.add(array[start]);
                    result.add(array[end]);
                } else {
                    if (result.get(0) * result.get(1) > array[start] * array[end]) {
                        result.set(0, array[start]);
                        result.set(1, array[end]);
                    }
                }
                start++;
                end--;
            } else if (temp < sum) {
                start++;
            } else {
                end--;
            }
        }
        return result;
    }
}
