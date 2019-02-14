package com.ping.wu.c30_stack;

import java.util.ArrayList;

/**
 * @author wuping
 * @date 2019/2/12
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
 */

public class Solution {
    private int min = Integer.MAX_VALUE;
    ArrayList<Integer> list = new ArrayList();

    public void push(int node) {
        if (list.size() == 0) {
            list.add(node);
            list.add(node);
            min = node;
            return;
        }
        if (node <= min) {
            list.add(min);
            min = node;
        }
        list.add(node);
    }

    public void pop() {
        int result = list.remove(list.size() - 1);
        if (result == min) {
            min = list.remove(list.size() - 1);
        }
    }

    public int top() {
        return list.get(list.size() - 1);
    }

    public int min() {
        return min;
    }
}
