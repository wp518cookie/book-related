package com.ping.wu.algo.sort;

/**
 * @author wuping
 * @date 2019-07-24
 */

public class BubbleSort implements Sortable {
    public static void main(String[] args) {
        NumberUtils.sort(NumberUtils.getRandomArs(20, 100), new BubbleSort());
    }

    @Override
    public String getSortName() {
        return "bubble sort";
    }

    @Override
    public void sort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            boolean changed = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    changed = true;
                }
            }
            if (!changed) {
                break;
            }
        }
    }
}
