package com.ping.wu.algo.sort;

/**
 * @author wuping
 * @date 2019-07-24
 */

public interface Sortable {
    void sort(int[] nums);

    String getSortName();

    default void swap(int[] arr, int i, int j) {
        if (i != j) {
            arr[i] = arr[i] + arr[j];
            arr[j] = arr[i] - arr[j];
            arr[i] = arr[i] - arr[j];
        }
    }
}
