package com.ping.wu.jvm.bytecode;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;

/**
 * @author wuping
 * @date 2019/4/11
 */

public class Foo1 {
    private String a;

    public Foo1(String a) {
        this.a = a;
    }

    private void say(String b) {
        System.out.println(a + ", " + b);
    }

    public static MethodHandles.Lookup getLookup() {
        return MethodHandles.lookup();
    }
}
