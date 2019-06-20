package com.ping.wu.jvm.bytecode;

/**
 * @author wuping
 * @date 2019/4/11
 */

public class OverrideTest {
    public static void main(String[] args) {
        Object o = new String("abc");
        OverrideTest t = new OverrideTest();
        t.say(o);
    }

    void say(Object o) {
        String v = String.valueOf(o);
        System.out.println(v);
    }

    void say(String s) {
        System.out.println(s);
    }
}
