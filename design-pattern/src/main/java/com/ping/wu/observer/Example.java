package com.ping.wu.observer;

/**
 * @author wuping
 * @date 2019-09-25
 */

public class Example {
    public static void main(String[] args) {
        SimpleSubject subject = new SimpleSubject();
        SimpleObserver observer = new SimpleObserver();
        subject.registerObserver(observer);
        subject.setVal(123);
    }
}
