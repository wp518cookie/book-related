package com.ping.wu.spring.learning.aop.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author wuping
 * @date 2020-08-10
 */

public class MthInvoker implements MethodInterceptor {
    private CglibTestSon s;

    public MthInvoker(CglibTestSon s) {
        this.s = s;
    }

    public void before() {
        System.out.println("aop before");
    }

    public void after() {
        System.out.println("aop after");
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        before();
        Object a = method.invoke(s, args);
        after();
        return a;
    }
}
