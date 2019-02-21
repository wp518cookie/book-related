package com.ping.wu.c40_get_least_numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author wuping
 * @date 2019/2/21
 * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
 */

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[]{4,5,1,6,2,7,3,8};
        List<Integer> result = solution.GetLeastNumbers_Solution(arr, 4);
        for (int i : result) {
            System.out.print(i);
            System.out.print(" ");
        }
    }

    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        int size = 0;
        int[] dump = new int[k];
        for (int i = 0; i < input.length; i++) {
            if (size < k) {
                dump[size] = input[i];
                siftUp(dump, i);
                size++;
            } else {
                if (dump[0] > input[i]) {
                    dump[0] = input[i];
                    siftDown(dump);
                }
            }
        }
        ArrayList<Integer> list = new ArrayList();
        for (int i : dump) {
            list.add(i);
        }
        return list;
    }

    public void siftUp(int[] arr, int k) {
        if (k == 0) {
            return;
        }
        for (; ; ) {
            if (k <= 0) {
                return;
            }
            if (arr[k] > arr[(k - 1) / 2]) {
                swap(arr, k, (k - 1) / 2);
                k = (k - 1) / 2;
            } else {
                return;
            }
        }
    }

    public void siftDown(int[] arr) {
        int k = 0;
        for (; ; ) {
            if (k >= arr.length - 1) {
                return;
            }
            int idx = 2 * k + 1;
            if (idx + 1 < arr.length - 1 && arr[idx] < arr[idx + 1]) {
                idx = idx + 1;
            }
            if (arr[idx] > arr[k]) {
                swap(arr, idx, k);
                k = idx;
            } else {
                break;
            }
        }
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
