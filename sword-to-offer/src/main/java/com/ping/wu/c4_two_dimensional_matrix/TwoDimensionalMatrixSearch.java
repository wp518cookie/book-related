package com.ping.wu.c4_two_dimensional_matrix;

/**
 * @author wuping
 * @date 2018/12/21
 */

public class TwoDimensionalMatrixSearch {
    public static void main(String[] args) {

    }

    public static boolean Find(int target, int[][] array) {
        if (array == null || array.length == 0 || array[0].length == 0) {
            return false;
        }
        int rows = array.length; int c = array[0].length - 1;
        int r = 0;
        while (r < array.length && c >= 0) {
            if (array[r][c] == target) {
                return true;
            } else if(array[r][c] < target) {
                r++;
            } else if(array[r][c] > target) {
                c--;
            }
        }
        return false;
    }
}
