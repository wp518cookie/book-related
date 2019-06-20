package com.ping.wu.jvm.classloader;

/**
 * @author wuping
 * @date 2019/4/11
 */

public class CloneTest {
    public static void main(String[] args) throws Exception {
        Person p1 = new Person(30, "wu");
        Person p2 = (Person) p1.clone();
        String result = p1.getName() == p2.getName() ? "Clone是浅拷贝" : "Clone是深拷贝";
        System.out.println(result);
    }
}
