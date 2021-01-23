package com.ping.wu.spring.learning.aop.cglib;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;

/**
 * @author wuping
 * @date 2020-08-10
 */

public class CglibProxyTest {
    public static void main(String[] args) throws Exception {
        CglibTestSon cglibTestSon = new CglibTestSon();
        Enhancer enhancer = new Enhancer();
        Callback s = new MthInvoker(cglibTestSon);
        enhancer.setSuperclass(CglibTestSon.class);
        Callback callbacks[] = new Callback[]{s};
        enhancer.setCallbacks(callbacks);
        CglibTestSon cglibTestSon2 = (CglibTestSon) enhancer.create();
        cglibTestSon2.gotoHome();
        cglibTestSon2.gotoSchool();

        cglibTestSon2.oneday();
        cglibTestSon2.onedayFinal();
    }
}
