package com.ping.wu.c12_has_path;

import java.util.Arrays;

/**
 * @author wuping
 * @date 2018/12/25
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一个格子开始，
 * 每一步可以在矩阵中向左，向右，向上，向下移动一个格子。如果一条路径经过了矩阵中的某一个格子，则之后不能再次进入
 * 这个格子。 例如 a b c e s f c s a d e e 这样的3 X 4 矩阵中包含一条字符串"bcced"的路径，但是矩阵中不包含
 * "abcb"路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入该格子。
 */

public class Solution {
    public static void main(String[] args) {
        String s = "ABCESFCSADEE";
        String t = "SEE";
        System.out.println(hasPath(s.toCharArray(), 3, 4, t.toCharArray()));
    }

    public static boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                    int[][] mark = new int[rows][cols];
                    int t = 0;
                    if (hasPath(matrix, i, j, rows, cols, mark, str, t)) {
                        return true;
                    }
            }
        }
        return false;
    }

    public static boolean hasPath(char[] martrix, int rowNum, int colNum, int rows, int cols, int[][] marked, char[] str, int t) {
        if (t >= str.length) {
            return true;
        }
        if (rowNum >= rows || rowNum < 0 || colNum >= cols || colNum < 0) {
            return false;
        }
        if (charAt(martrix, rowNum, colNum, cols) == str[t] && marked[rowNum][colNum] == 0) {

            t++;
            marked[rowNum][colNum] = 1;
            return hasPath(martrix, rowNum - 1, colNum, rows, cols, Arrays.copyOf(marked, marked.length), str, t) ||
                    hasPath(martrix, rowNum + 1, colNum, rows, cols, Arrays.copyOf(marked, marked.length), str, t) ||
                    hasPath(martrix, rowNum, colNum - 1, rows, cols, Arrays.copyOf(marked, marked.length), str, t) ||
                    hasPath(martrix, rowNum, colNum + 1, rows, cols, Arrays.copyOf(marked, marked.length), str, t);
        } else {
            return false;
        }
    }

    public static char charAt(char[] matrix, int rowNum, int colNum, int cols) {
        return matrix[rowNum * cols + colNum];
    }
}
