package com.ping.wu.strings.stringsorts.review;

import java.util.Arrays;

/**
 * @author wuping
 * @date 2020-08-07
 */

public class LSD {
    public static void main(String[] args) {
        String[] s = new String[]{"tes", "bed", "bug", "dad", "yes", "zoo" };
        sort(s, 3);
        System.out.println(Arrays.toString(s));
    }

    /**
     * 相同长度
     * @param s
     * @param w
     */
    public static void sort(String[] s, int w) {
        for (int i = w - 1; i >= 0; i--) {
            int[] count = new int[27];
            for (int j = 0; j < s.length; j++) {
                count[s[j].charAt(i) - 96] ++;
            }
            for (int j = 1; j < count.length; j++) {
                count[j] = count[j - 1] + count[j];
            }
            String[] aux = new String[27];
            for (int j = 0; j < s.length; j++) {
                aux[count[s[j].charAt(i) - 97]++] = s[j];
            }
            for (int j = 0; j < s.length; j++) {
                s[j] = aux[j];
            }
        }
    }
}
