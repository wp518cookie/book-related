package com.ping.wu.c10_3_rectcover;

/**
 * @author wuping
 * @date 2018/12/24
 */

public class Solution {
    public static void main(String[] args) {

    }

    public static int RectCover(int target) {
        if (target == 1) {
            return 1;
        } else if (target == 2) {
            return 2;
        } else {
            return RectCover(target - 1) + RectCover(target - 2);
        }
    }
}
