package com.ping.wu.spring.learning.aop.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author wuping
 * @date 2020-08-06
 */

public class Client {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        TestServiceImpl testService = new TestServiceImpl();
        ClassLoader classLoader = testService.getClass().getClassLoader();
        Class[] interfaces = testService.getClass().getInterfaces();
        InvocationHandler logHandler = new LogHandler(testService);
        TestService proxy = (TestService) Proxy.newProxyInstance(classLoader, interfaces, logHandler);
        proxy.test();
        ProxyUtils.generateClassFile(proxy.getClass(), "TestServiceProxy");
    }
}
