package com.ping.wu.se.concurrent.cas;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author wuping
 * @date 2019/4/10
 */

public class AtomicStampedReferenceTest {
    public static void main(String[] args) {
        String str1 = "aaa";
        String str2 = "bbb";
        AtomicStampedReference<String> reference = new AtomicStampedReference(str1, 1);
        System.out.println("reference1 val : " + reference.getReference());
        System.out.println("reference1 stamp : " + reference.getStamp());
        boolean result = reference.compareAndSet(str1, str2, 2, 3);
        System.out.println("reference2 val : " + reference.getReference());
        System.out.println("reference2 stamp : " + reference.getStamp());
        System.out.println("reference2 result : " + result);
        boolean result2 = reference.compareAndSet(str1, str2, 1, 2);
        System.out.println("reference3 val : " + reference.getReference());
        System.out.println("reference3 stamp : " + reference.getStamp());
        System.out.println("reference3 result : " + result2);
    }
}
