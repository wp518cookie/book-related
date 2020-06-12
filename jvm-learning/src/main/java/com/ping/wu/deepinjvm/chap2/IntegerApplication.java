package com.ping.wu.deepinjvm.chap2;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @author wuping
 * @date 2020-06-11
 */

public class IntegerApplication {
    public static void main(String[] args) {
        List<Integer> intList = new LinkedList();
        for (int i = 0; i < 2000000; i++) {
            Integer num = new Integer(i);
            intList.add(num);
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("application is running...");
        String tmp = scanner.nextLine();
        System.exit(0);
    }
}
