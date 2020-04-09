package com.ping.wu.algo.sort;

/**
 * @author wuping
 * @date 2019-07-24
 */

public class InsertSort implements Sortable {

    public static void main(String[] args) {
        NumberUtils.sort(NumberUtils.getRandomArs(20, 100), new InsertSort());
    }

    @Override
    public void sort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            int j = i;
            for (; j > 0; j--) {
                if (nums[j - 1] > val) {
                    nums[j] = nums[j - 1];
                } else {
                    break;
                }
            }
            nums[j] = val;
        }
        return;
    }

    @Override
    public String getSortName() {
        return "insert sort";
    }
}
