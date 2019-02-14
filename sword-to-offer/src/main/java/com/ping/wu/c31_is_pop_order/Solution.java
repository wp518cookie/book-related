package com.ping.wu.c31_is_pop_order;

/**
 * @author wuping
 * @date 2019/2/12
 */

public class Solution {
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        if ((pushA == null || pushA.length == 0) && (popA == null || popA.length == 0)) {
            return true;
        }
        if (pushA.length != popA.length) {
            return false;
        }
        int length = pushA.length;
        for (int i = 0; i < length; i++) {
            if (pushA[i] != popA[length - i - 1]) {
                return false;
            }
        }
        return true;
    }
}
