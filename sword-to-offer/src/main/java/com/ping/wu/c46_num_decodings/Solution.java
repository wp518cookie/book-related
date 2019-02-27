package com.ping.wu.c46_num_decodings;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wuping
 * @date 2019/2/25
 * todo 动态规划做
 * 给定一个数字，按照如下规则翻译成字符串：1 翻译成“a”，2 翻译成“b”... 26 翻译成“z”。
 * 一个数字有多种翻译可能，例如 12258 一共有 5 种，分别是 abbeh，lbeh，aveh，abyh，lyh。实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 */

public class Solution {

    public static void main(String[] args) {
//        String s = "61";
//        System.out.println(Integer.valueOf(String.format("%c%c", s.charAt(0), s.charAt(1))));
        System.out.println(numDecodings("9371597631128776948387197132267188677349946742344217846154932859125134924241649584251978418763151253"));
    }

    public static int numDecodings(String s) {
        return numDecodings(s, 0);
    }

    public static int numDecodings(String s, int i) {
        if (i < s.length() && s.charAt(i) == '0') {
            return 0;
        } else if (i == s.length() - 1 && s.charAt(i) != '0') {
            return 1;
        } else if (i >= s.length()) {
            return 1;
        } else {
            if (i + 1 <= s.length() - 1 &&
                    (s.charAt(i) == '1' || (s.charAt(i) == '2' && s.charAt(i + 1) < '7'))) {
                return numDecodings(s, i + 1) + numDecodings(s, i + 2);
            } else {
                return numDecodings(s, i + 1);
            }
        }
    }

    private static String transfer(String t) {
        char temp = (char) (Integer.valueOf(t) + 64);
        return String.valueOf(temp);
    }

    public static int dp(String s) {
        return 0;
    }
}