package com.ping.wu.jvminaction.chap3;

/**
 * @author wuping
 * @date 2020-03-25
 */

public class HeapAlloc {
    public static void main(String[] args) {
        System.out.print("maxMemory=");
        System.out.println(Runtime.getRuntime().maxMemory() / 1000+" k");
        System.out.print("free mem=");
        System.out.println(Runtime.getRuntime().freeMemory() / 1000+" k");
        System.out.print("total mem=");
        System.out.println(Runtime.getRuntime().totalMemory() / 1000+" k");

        byte[] b=new byte[1*1024*1024];
        System.out.println("分配了1M空间给数组");

        System.out.print("maxMemory=");
        System.out.println(Runtime.getRuntime().maxMemory() / 1000+" k");
        System.out.print("free mem=");
        System.out.println(Runtime.getRuntime().freeMemory() / 1000+" k");
        System.out.print("total mem=");
        System.out.println(Runtime.getRuntime().totalMemory() / 1000+" k");

        b=new byte[4*1024*1024];
        System.out.println("分配了4M空间给数组");

        System.out.print("maxMemory=");
        System.out.println(Runtime.getRuntime().maxMemory() / 1000+" k");
        System.out.print("free mem=");
        System.out.println(Runtime.getRuntime().freeMemory() / 1000+" k");
        System.out.print("total mem=");
        System.out.println(Runtime.getRuntime().totalMemory() / 1000+" k");
    }
}
