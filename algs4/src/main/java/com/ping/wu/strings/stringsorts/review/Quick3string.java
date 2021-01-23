package com.ping.wu.strings.stringsorts.review;

import java.util.Arrays;

/**
 * @author wuping
 * @date 2020-08-07
 */

public class Quick3string {
    public static void main(String[] args) {
        String[] s = new String[]{
                "ads", "oija", "kiwww", "basdf", "bqwe", "ba"
        };
        sort(s);
        System.out.println(Arrays.toString(s));
    }

    public static void sort(String[] a) {
        sort(a, 0, a.length - 1, 0);
    }

    private static void sort(String[] s, int lo, int hi, int idx) {
        if (lo >= hi) {
            return;
        }
        int v = charAt(s[lo], idx);
        int lt = lo;
        int gt = hi;
        int i = lo + 1;
        while (i <= gt) {
            int t = charAt(s[i], idx);
            if (t < v) {
                exchange(s, i, lt++);
            } else if (t > v) {
                exchange(s, i, gt--);
            } else {
                i++;
            }
        }
        sort(s, lo, lt - 1, idx);
        if (v > 0) {
            sort(s, lt, gt, idx + 1);
        }
        sort(s, gt + 1, hi, idx);
    }

    private static int charAt(String s, int idx) {
        if (idx < s.length()) {
            return s.charAt(idx);
        } else {
            return -1;
        }
    }

    private static void exchange(String[] arr, int i, int j) {
        String t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
