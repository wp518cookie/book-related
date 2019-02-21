package com.ping.wu.algo.backtrace;

import java.util.ArrayList;

/**
 * @author wuping
 * @date 2019/2/20
 */

public class GenerateParentheses {
    public static void main(String[] args) {
        int n = 3;
        //左括号和右括号都各有n个
        int leftnum = n, rightnum = n;
        //用于存放解空间
        ArrayList<String> results = new ArrayList<String>();
        parentheses("", results, leftnum, rightnum);
        for (String s : results) {
            System.out.println(s);
        }
    }

    public static void parentheses(String sublist, ArrayList<String> results, int leftnum, int rightnum) {
        if (leftnum == 0 && rightnum == 0) {
            results.add(sublist);
        }
        //选择和条件。对于不同的if顺序，输出的结果顺序是不一样的，但是构成一样的解空间
        if (rightnum > leftnum) {
            parentheses(sublist + ")", results, leftnum, rightnum - 1);
        }
        if (leftnum > 0) {
            parentheses(sublist + "(", results, leftnum - 1, rightnum);
        }
    }
}
