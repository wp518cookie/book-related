package com.ping.wu.jvm.bytecode;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;

/**
 * @author wuping
 * @date 2019/4/11
 */

public class Foo {
    private static void bar(String o) {
        System.out.println(o.toString());
    }

    public static MethodHandles.Lookup lookup() {
        return MethodHandles.lookup();
    }

    public static void main(String[] args) throws Throwable {
        MethodHandles.Lookup l = Foo.lookup();
        Method m = Foo.class.getDeclaredMethod("bar", String.class);
        MethodHandle mh0 = l.unreflect(m);
        mh0.invoke("hello world1");
        MethodType t = MethodType.methodType(void.class, String.class);
        MethodHandle mh1 = l.findStatic(Foo.class, "bar", t);
        mh1.invoke("hello world2");
    }
}
