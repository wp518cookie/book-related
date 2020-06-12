package com.ping.wu.strings.stringsorts;

import java.util.Arrays;

/**
 * @author wuping
 * @date 2020-04-11
 */

public class LSD {
    public static void sort(String[] s, int w) {
        int n = s.length;
        for (int i = w - 1; i >= 0; i--) {
            int[] count = new int[27];
            for (int j = 0; j < n; j++) {
                count[s[j].charAt(i) - 96]++;
            }
            for (int j = 1; j < 27; j++) {
                count[j] = count[j - 1] + count[j];
            }
            String[] aux = new String[n];
            for (int j = 0; j < n; j++) {
                aux[count[s[j].charAt(i) - 97]++] = s[j];
            }
            for (int j = 0; j < n; j++) {
                s[j] = aux[j];
            }
        }
    }

    //todo 基数排序

    public static void main(String[] args) {
        String[] s = new String[]{"bed", "bug", "dad", "yes", "zoo" };
        sort(s, 3);
        System.out.println(Arrays.toString(s));
    }
}
