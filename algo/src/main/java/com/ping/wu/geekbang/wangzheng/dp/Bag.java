package com.ping.wu.geekbang.wangzheng.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wuping
 * @date 2020-03-31
 */

public class Bag {
    private int maxW = Integer.MIN_VALUE;

    public void recursion(int[] weight, int n, int w) {
        recursion(0, 0, w, weight);
    }

    private void recursion(int start, int weight, int limit, int[] arr) {
        if (weight == limit || start >= arr.length) {
            maxW = Math.max(maxW, weight);
            return;
        }
        for (int i = start; i < arr.length; i++) {
            if (weight + arr[i] > limit) {
                continue;
            }
            recursion(i + 1, weight + arr[i], limit, arr);
        }
    }

    public static void main(String[] args) {
        Bag bag = new Bag();
        int[] arr = new int[]{2, 2, 4, 6, 3};
        bag.recursion(arr, 5, 9);
        System.out.println(bag.maxW);
    }

    public int knapsack(int[] weight, int n, int w) {
        boolean[][] states = new boolean[n][w + 1];
        states[0][0] = true;
        if (weight[0] <= w) {
            states[0][weight[0]] = true;
        }
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j <= w; ++j) {
                if (states[i - 1][j]) {
                    states[i][j] = states[i - 1][j];
                }
            }
            for (int j = 0; j <= w - weight[i]; ++j) {
                if (states[i - 1][j]) {
                    states[i][j + weight[i]] = true;
                }
            }
        }
        for (int i = w; i >= 0; --i) {
            if (states[n - 1][i]) {
                return i;
            }
        }
        return 0;
    }

    public int knapsack2(int[] items, int n, int w) {
        boolean[] states = new boolean[w + 1];
        states[0] = true;
        if (items[0] <= w) {
            states[items[0]] = true;
        }
        for (int i = 1; i < n; ++i) {
            for (int j = w - items[i]; j >= 0; j--) {
                if (states[j]) {
                    states[j + items[i]] = true;
                }
            }
        }
        for (int i = w; i >= 0; i--) {
            if (states[i]) {
                return i;
            }
        }
        return 0;
    }
}
