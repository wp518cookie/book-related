package com.ping.wu.c9_realize_queue_by_two_stack;

import java.util.Stack;

/**
 * @author wuping
 * @date 2018/12/23
 */

public class Solution {
    Stack<Integer> stack1 = new Stack();
    Stack<Integer> stack2 = new Stack();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if (stack2.empty()) {
            while (!stack1.empty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }
}
