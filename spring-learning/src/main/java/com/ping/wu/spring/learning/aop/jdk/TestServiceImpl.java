package com.ping.wu.spring.learning.aop.jdk;

/**
 * @author wuping
 * @date 2020-08-06
 */

public class TestServiceImpl implements TestService, TestService1 {

    @Override
    public void test() {
        System.out.println("test method execute");
    }

    @Override
    public void test1() {
        System.out.println("test1 method execute");
    }
}
