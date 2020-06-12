package com.ping.wu.deepinjvm.chap2;

import java.util.LinkedList;
import java.util.List;

/**
 * @author wuping
 * @date 2020-06-10
 */

public class HeapOOMTest {
    public static void main(String[] args) {
        List<byte[]> list = new LinkedList<>();
        int count = 0;
        while (true) {
            count++;
            byte[] t = new byte[1024 * 1024];
            System.out.println("oom test :" + count);
            list.add(t);
        }
    }
}
