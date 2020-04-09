package com.ping.wu.geekbang.wangzheng.dp;

import java.util.Arrays;

/**
 * @author wuping
 * @date 2020-03-31
 */

public class BagPro {
    private int maxVal = Integer.MIN_VALUE;

    public static void main(String[] args) {
        int[] items = {2, 2, 4, 6, 3};
        int[] value = {3, 4, 8, 9, 6};
        BagPro bagPro1 = new BagPro();
        bagPro1.recursion(items, value, 5, 9);
        System.out.println(bagPro1.maxVal);
        BagPro bagPro2 = new BagPro();
        bagPro2.recursionByHuangZheng(items, value, 5, 9);
        System.out.println(bagPro2.maxVal);

        BagPro bagPro3 = new BagPro();
        bagPro3.knapsack(items, value, 5, 9);
    }

    public int recursion(int[] items, int[] values, int n, int w) {
        recursion(0, items, values, n, w, 0, 0);
        return maxVal;
    }

    private void recursion(int start, int[] items, int[] values, int n, int w, int currentWeight, int currentVal) {
        if (currentVal > maxVal) {
            maxVal = currentVal;
        }
        for (int i = start; i < n; i++) {
            if (items[i] + currentWeight <= w) {
                recursion(i + 1, items, values, n, w, items[i] + currentWeight, currentVal + values[i]);
            }
        }
    }

    public void recursionByHuangZheng(int[] items, int[] values, int n, int w) {
        recursionByHuangZheng(items, values, 0, 0, w, n, 0);
    }

    private void recursionByHuangZheng(int[] items, int[] values, int i, int cw, int w, int n, int cv) {
        if (cw == w || i == n) {
            if (cv > maxVal) {
                maxVal = cv;
            }
            return;
        }
        recursionByHuangZheng(items, values, i + 1, cw, w, n, cv);
        if (cw + items[i] <= w) {
            recursionByHuangZheng(items, values, i + 1, cw + items[i], w, n, cv + values[i]);
        }
    }

    public void knapsack(int[] items, int[] values, int n, int w) {
        int[] states = new int[w + 1];
        for (int i = 0; i < states.length; i++) {
            states[i] = -1;
        }
        states[0] = 0;
        for (int i = 0; i < items.length; i++) {
            int temp = items[i];
            for (int j = w - temp; j >= 0; j--) {
                if (states[j] >= 0) {
                    int newVal = states[j] + values[i];
                    if (newVal > states[j + temp]) {
                        states[j + temp] = states[j] + values[i];
                    }
                }
            }
        }
        System.out.println(Arrays.toString(states));
    }

    public int knapsackByHuangZheng(int[] weight, int[] values, int n, int w) {
        int[][] states = new int[n][w + 1];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < w + 1; ++j) {
                states[i][j] = -1;
            }
        }
        states[0][0] = 0;
        if (weight[0] <= w) {
            states[0][weight[0]] = values[0];
        }
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j <= w; ++j) {
                if (states[i - 1][j] >= 0) {
                    states[i][j] = states[i - 1][j];
                }
            }
            for (int j = 0; j <= w - weight[i]; ++j) {
                if (states[i - 1][j] >= 0) {
                    int v = states[i - 1][j] + values[i];
                    if (v > states[i][j + weight[i]]) {
                        states[i][j + weight[i]] = v;
                    }
                }
            }
        }
        int maxValue = -1;
        for (int j = 0; j <= w; j++) {
            if (states[n - 1][j] > maxValue) {
                maxValue = states[n - 1][j];
            }
        }
        return maxValue;
    }
}
