package com.ping.wu.algo.other;

import java.util.Random;

/**
 * @author wuping
 * @date 2019/2/18
 * 一个圆环上有100个灯泡，灯泡有打开关闭两种状态，灯泡的状态随机，按一个灯泡，
 * 相邻的两个灯泡的状态也发生一次变化。比如 暗-亮-暗，按中间灯泡，变化为 亮-暗-亮
 */

public class Bulb {

    public static void main(String[] args) {
        for (int j = 0; j < 100; j++) {
            int[] arr = new int[100];
            Random random = new Random();
            for (int i = 0; i < 100; i++) {
                arr[i] = random.nextBoolean() ? -1 : 1;
            }
            operate(arr);
            for (int i = 0; i < 100; i++) {
                if (arr[i] != 1) {
                    System.out.println(i + " 不符合要求");
                }
            }
        }
    }


    public static void operate(int[] arr) {
        for (int i = 0; i < arr.length - 3; i++) {
            if (arr[i] == -1) {
                operate(arr, i + 1);
            }
        }
        // 001
        if (arr[97] == -1 && arr[98] == -1 && arr[99] == 1) {
            operate(arr, 99);
            propagate(arr, 99, 97);
            operate(arr, 96);
            // 010
        } else if (arr[97] == -1 && arr[98] == 1 && arr[99] == -1) {
            operate(arr, 97);
            propagate(arr, 98, 96);
            operate(arr, 95);
            // 011
        } else if (arr[97] == -1 && arr[98] == 1 && arr[99] == 1) {
            operate(arr, 96);
            operate(arr, 97);
            propagate(arr, 97, 95);
            operate(arr, 94);
            // 100
        } else if (arr[97] == 1 && arr[98] == -1 && arr[99] == -1) {
            operate(arr, 97);
            propagate(arr, 99, 96);
            operate(arr, 96);
            //101
        } else if (arr[97] == 1 && arr[98] == -1 && arr[99] == 1) {
            operate(arr, 98);
            operate(arr, 97);
            propagate(arr, 98, 96);
            operate(arr, 95);
            // 110
        } else if (arr[97] == 1 && arr[98] == 1 && arr[99] == -1) {
            operate(arr, 98);
            operate(arr, 99);
            propagate(arr, 99, 97);
            operate(arr, 96);
        } else if (arr[97] == -1 && arr[98] == -1 && arr[99] == -1) {
            operate(arr, 98);
        } else {
            return;
        }
    }

    public static void operate(int[] arr, int idx) {
        int length = arr.length;
        arr[idx] *= -1;
        if (idx == 0) {
            arr[length - 1] *= -1;
            arr[idx + 1] *= -1;
        } else if (idx == length - 1) {
            arr[idx - 1] *= -1;
            arr[0] *= -1;
        } else {
            arr[idx + 1] *= -1;
            arr[idx - 1] *= -1;
        }
    }

    public static void operateIfShutDown(int[] arr, int idx) {
        if (idx == 0) {
            if (arr[arr.length -1] == 1) {
                return;
            }
        } else {
            if (arr[idx - 1] == 1) {
                return;
            }
        }
        int length = arr.length;
        arr[idx] *= -1;
        if (idx == 0) {
            arr[length - 1] *= -1;
            arr[idx + 1] *= -1;
        } else if (idx == length - 1) {
            arr[idx - 1] *= -1;
            arr[0] *= -1;
        } else {
            arr[idx + 1] *= -1;
            arr[idx - 1] *= -1;
        }
    }

    public static void propagate(int[] arr, int idx, int end) {
        while (true) {
            operateIfShutDown(arr, idx);
            idx++;
            if (idx == 100) {
                idx = 0;
            }
            if (idx + 1 == end) {
                break;
            }
        }
    }
}
