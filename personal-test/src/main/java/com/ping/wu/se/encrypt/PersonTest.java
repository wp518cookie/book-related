package com.ping.wu.se.encrypt;

import com.alibaba.fastjson.JSON;

import java.util.Map;

/**
 * @author wuping
 * @date 2019/1/17
 */

public class PersonTest {
    private String name;
    private String age;

    public PersonTest() {

    }

    public PersonTest(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public static void main(String[] args) {

    }
}
