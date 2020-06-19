package com.ping.wu.deepinjvm.chap3;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @author wuping
 * @date 2020-06-15
 */

public class MyWeakReference extends WeakReference<Person> {
    public Person p;

    public MyWeakReference(Person referent) {
        super(referent);
        p = referent;
    }

    public MyWeakReference(Person referent, ReferenceQueue<? super Person> q) {
        super(referent, q);
    }
}
