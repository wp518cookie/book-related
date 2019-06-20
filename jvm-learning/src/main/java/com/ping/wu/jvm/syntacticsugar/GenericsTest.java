package com.ping.wu.jvm.syntacticsugar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wuping
 * @date 2019-04-15
 */

public class GenericsTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList();
        list.add("a");
        list.add("b");
        for (String t : list) {
            System.out.println(t);
        }
        String s = test("hello");
        System.out.println(s);
    }

    private static <T> T test(T s) {
        return s;
    }
}
