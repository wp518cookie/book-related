package com.ping.wu.c10_2_jumpfloor;

/**
 * @author wuping
 * @date 2018/12/24
 */

public class Solution {
    public static int JumpFloor(int target) {
        if (target == 0) {
            return 0;
        } else if (target == 1) {
            return 1;
        } else if (target == 2) {
            return 2;
        } else {
            return JumpFloor(target - 1) + JumpFloor(target - 2);
        }
    }
}
