package com.ping.wu.deepinjvm.chap3;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/**
 * @author wuping
 * @date 2020-06-15
 */

public class MySoftReference<T> extends SoftReference<T> {
    T t;

    public MySoftReference(T referent) {
        super(referent);
        this.t = referent;
    }

    public MySoftReference(T referent, ReferenceQueue<T> q) {
        super(referent, q);
        this.t = referent;
    }
}
