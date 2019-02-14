package com.ping.wu.c29_print_matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wuping
 * @date 2019/2/12
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，例如，如果输入如下4 X 4矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
 */

public class Solution {
    public ArrayList<Integer> printMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new ArrayList();
        }
        ArrayList<Integer> result = new ArrayList();
        int row = matrix.length;
        int col = matrix[0].length;
        int topRow = 0;
        int bottomRow = row - 1;
        int leftCol = 0;
        int rightCol = col - 1;
        while (true) {
            for (int i = leftCol; i <= rightCol; i++) {
                result.add(matrix[leftCol][i]);
            }
            for (int i = topRow + 1; i <= bottomRow; i++) {
                result.add(matrix[i][rightCol]);
            }
            if (topRow < bottomRow) {
                for (int i = rightCol - 1; i >= leftCol; i--) {
                    result.add(matrix[bottomRow][i]);
                }
            }
            if (leftCol < rightCol) {
                for (int i = bottomRow - 1; i >= topRow + 1; i--) {
                    result.add(matrix[i][leftCol]);
                }
            }
            topRow++;
            bottomRow--;
            leftCol++;
            rightCol--;
            if (topRow > bottomRow || leftCol > rightCol) {
                break;
            }
        }
        return result;
    }
}
