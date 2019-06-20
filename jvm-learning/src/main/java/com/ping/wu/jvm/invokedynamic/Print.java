package com.ping.wu.jvm.invokedynamic;

/**
 * @author wuping
 * @date 2019-04-15
 */

@FunctionalInterface
public interface Print<T> {
    void print(T x);
}
