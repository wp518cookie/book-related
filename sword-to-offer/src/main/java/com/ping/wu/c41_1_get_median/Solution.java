package com.ping.wu.c41_1_get_median;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wuping
 * @date 2019/2/21
 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，
 * 那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，
 * 那么中位数就是所有数值排序之后中间两个数的平均值。我们使用Insert()方法读取数据流，
 * 使用GetMedian()方法获取当前读取数据的中位数。
 */

public class Solution {
    private List<Integer> leftList = new ArrayList();
    private List<Integer> rightList = new ArrayList();

    public static void main(String[] args) {
        Solution solution = new Solution();
//        for (int i = 1; i < 10; i++) {
//            solution.insert(i);
//        }
        solution.insert(5);
        solution.insert(2);
        solution.insert(3);
        solution.insert(4);
        solution.insert(1);
        solution.insert(6);
        System.out.println(solution.GetMedian());
    }

    public void insert(Integer num) {
        int leftSize = leftList.size();
        int rightSize = rightList.size();
        if (leftSize == 0) {
            leftList.add(num);
        } else if (rightSize == 0) {
            if (leftList.get(0) > num) {
                rightList.add(leftList.get(0));
                leftList.set(0, num);
            } else {
                rightList.add(num);
            }
        } else if (leftSize > rightSize) {
            if (leftList.get(0) > num) {
                rightList.add(leftList.get(0));
                leftList.set(0, num);
                siftDownBiggest(leftList);
                siftUpSmallest(rightList, rightList.size() - 1);
            } else {
                rightList.add(num);
                siftUpSmallest(rightList, rightList.size() - 1);
            }
        } else if (leftSize == rightSize) {
            if (leftList.get(0) > num) {
                leftList.add(num);
                siftUpBiggest(leftList, leftList.size() - 1);
            } else {
                rightList.add(num);
                siftUpSmallest(rightList, rightList.size() - 1);
            }
        } else {
            if (rightList.get(0) < num) {
                leftList.add(rightList.get(0));
                rightList.set(0, num);
                siftUpBiggest(leftList, leftList.size() - 1);
                siftDownSmallest(rightList);
            } else {
                leftList.add(num);
                siftUpBiggest(leftList, leftList.size() - 1);
            }
        }
    }

    // 最大堆
    public void siftUpBiggest(List<Integer> list, int k) {
        for (; ; ) {
            if (k <= 0) {
                return;
            }
            if (list.get((k - 1) >> 1) < list.get(k)) {
                swapDuringList(list, k, (k - 1) >> 1);
                k = (k - 1) >> 1;
            } else {
                return;
            }
        }
    }

    public void siftDownBiggest(List<Integer> list) {
        int k = 0;
        for (; ; ) {
            if (2 * k + 1 > list.size() - 1) {
                return;
            }
            int idx = (k << 1) + 1;
            if (idx + 1 < list.size() - 1 && list.get(idx) < list.get(idx + 1)) {
                idx++;
            }
            if (list.get(idx) > list.get(k)) {
                swapDuringList(list, idx, k);
                k = idx;
            } else {
                return;
            }
        }
    }

    // 最小堆
    public void siftUpSmallest(List<Integer> list, int k) {
        for (; ; ) {
            if (k <= 0) {
                return;
            }
            if (list.get((k - 1) >> 1) > list.get(k)) {
                swapDuringList(list, k, (k - 1) >> 1);
                k = (k - 1) >> 1;
            } else {
                return;
            }
        }
    }

    public void siftDownSmallest(List<Integer> list) {
        int k = 0;
        for (; ; ) {
            if (2 * k + 1 > list.size() - 1) {
                return;
            }
            int idx = (k << 1) + 1;
            if (idx + 1 < list.size() - 1 && list.get(idx) > list.get(idx + 1)) {
                idx++;
            }
            if (list.get(idx) < list.get(k)) {
                swapDuringList(list, idx, k);
                k = idx;
            } else {
                return;
            }
        }
    }

    public Double GetMedian() {
        if (leftList.size() == rightList.size()) {
            return ((double) leftList.get(0) + (double) rightList.get(0)) / 2.0;
        } else {
            return leftList.size() > rightList.size() ? (double) leftList.get(0) : (double) rightList.get(0);
        }
    }

    public void swapDuringList(List<Integer> list, int idx1, int idx2) {
        int temp = list.get(idx1);
        list.set(idx1, list.get(idx2));
        list.set(idx2, temp);
    }
}
