package com.ping.wu.spring.learning.aop.cglib;

/**
 * @author wuping
 * @date 2020-08-10
 */

public class CglibTestSon {
    public CglibTestSon() {

    }

    public void gotoHome() {
        System.out.println("============gotoHome============");
    }

    public void gotoSchool() {
        System.out.println("============gotoSchool============");
    }

    public void oneday() {
        gotoHome();
        gotoSchool();
    }

    public final void onedayFinal() {
        gotoHome();
        gotoSchool();
    }
}


