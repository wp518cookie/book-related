package com.ping.wu.c13_moving_count;

/**
 * @author wuping
 * @date 2018/12/25
 * 地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，但是不能进入行坐标和列坐标的数位之和大于k的格子。
 * 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
 */

public class Solution {
    public static final int[][] NEXT = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) {

    }

    public static int movingCount(int threshold, int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

            }
        }
    }

    public static boolean check(int rowNum, int colNum, int threshold) {
        int t = 0;
        while (rowNum >= 10) {
            t += rowNum % 10;
            rowNum = rowNum / 10;
        }
        t += rowNum;
        while (colNum >= 10) {
            t += colNum % 10;
            colNum = colNum / 10;

        }
    }
}
