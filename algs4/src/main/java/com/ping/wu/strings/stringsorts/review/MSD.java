package com.ping.wu.strings.stringsorts.review;

import java.util.Arrays;

/**
 * @author wuping
 * @date 2020-08-07
 */

public class MSD {
    public static void main(String[] args) {
        String[] arr = new String[]{
                "ads", "oija", "kiwww", "basdf", "bqwe", "ba"
        };
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(String[] s) {
        recursion(s, 0, s.length - 1, 0);
    }

    public static void recursion(String[] s, int lo, int hi, int idx) {
        if (lo >= hi) {
            return;
        }
        int[] count = new int[258];
        for (int i = lo; i <= hi; i++) {
            count[getIdx(s[i], idx) + 2]++;
        }
        for (int i = 1; i < count.length; i++) {
            count[i] = count[i - 1] + count[i];
        }
        String[] aux = new String[hi - lo + 1];
        for (int i = lo; i <= hi; i++) {
            aux[count[getIdx(s[i], idx) + 1]++] = s[i];
        }
        for (int i = lo; i <= hi; i++) {
            s[i] = aux[i - lo];
        }
        for (int i = 0; i < 257; i++) {
            recursion(s, lo + count[i], lo + count[i + 1] - 1, idx + 1);
        }
    }

    private static int getIdx(String s, int t) {
        if (s.length() <= t) {
            return -1;
        }
        return s.charAt(t);
    }
}
