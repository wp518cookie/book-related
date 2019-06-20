package com.ping.wu.jvm.bytecode;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * @author wuping
 * @date 2019/4/11
 */

public class MethodHandleTest2 {
    public static void main(String[] args) {
        try {
            MethodType methodType = MethodType.methodType(void.class, String.class);
            MethodHandle mh = Foo1.getLookup().findVirtual(Foo1.class, "say", methodType);
            Foo1 foo1 = new Foo1("world");
            mh.invoke(foo1, "hello");
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
