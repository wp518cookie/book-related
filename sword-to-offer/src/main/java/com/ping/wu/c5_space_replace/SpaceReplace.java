package com.ping.wu.c5_space_replace;

/**
 * @author wuping
 * @date 2018/12/21
 */

public class SpaceReplace {
    public static void main(String[] args) {
        String s = "We Are Happy";
        System.out.println(replaceSpace(new StringBuffer(s)));
    }

    public static String replaceSpace(StringBuffer str) {
        if (str == null || str.length() == 0) {
            return str == null ? null : "";
        }
        int p1 = str.length();
        for (int i = 0; i < p1; i++) {
            if (str.charAt(i) == ' ') {
                str.append("  ");
            }
        }
        int p2 = str.length();
        for (int i = p1 - 1; i >= 0; i--) {
            if (str.charAt(i) == ' ') {
                str.setCharAt(--p2, '0');
                str.setCharAt(--p2, '2');
                str.setCharAt(--p2, '%');
            } else {
                str.setCharAt(--p2, str.charAt(i));
            }
        }
        return str.toString();
    }
}
