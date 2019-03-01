package com.ping.wu.c58_1_reverse_sentence;

import java.util.Stack;

/**
 * @author wuping
 * @date 2019/3/1
 */

public class Solution {
    public static void main(String[] args) {
        System.out.println(ReverseSentence("I am a student."));
    }
    public static String ReverseSentence(String str) {
        if (str == null || str.length() <= 1) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack();
        char blank = ' ';
        for (int i = str.length() - 1; i >= 0; i--) {
            if (str.charAt(i) != blank) {
                stack.push(str.charAt(i));
            } else {
                while (!stack.isEmpty()) {
                    sb.append(stack.pop());
                }
                sb.append(blank);
            }
        }
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }
}
