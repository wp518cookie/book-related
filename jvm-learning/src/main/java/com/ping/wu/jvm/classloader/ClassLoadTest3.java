package com.ping.wu.jvm.classloader;

import com.ping.wu.jvm.reference.FinalizeEscapeGC;

/**
 * @author wuping
 * @date 2019/4/11
 * -XX:+TraceClassLoading
 */

public class ClassLoadTest3 {
    public static void main(String[] args) {
        System.out.println(FinalFieldClass.constString);
    }

    private static class FinalFieldClass {
        // 编译生成class字节码时会对常量有优化，直接放到常量池
        public static final String constString = "CONST";
        static {
            System.out.println("FinalFieldClass init");
        }
    }
}
