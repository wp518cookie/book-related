package com.ping.wu.jvm.classloader;

/**
 * @author wuping
 * @date 2019/4/11
 */

public class Person implements Cloneable {
    private int age;
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public Person() {

    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Person person =  (Person)super.clone();
        return person;
    }
}
