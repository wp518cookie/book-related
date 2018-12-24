package com.ping.wu.c10_4_jumpfloorII;

/**
 * @author wuping
 * @date 2018/12/23
 */

public class Solution {
    public static void main(String[] args) {

    }

    public static int JumpFloor(int target) {
        if (target == 0) {
            return 1;
        } else if (target == 1) {
            return 1;
        } else {
            return 2 * JumpFloor(target - 1);
        }
    }
}
