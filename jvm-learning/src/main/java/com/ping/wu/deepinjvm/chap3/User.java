package com.ping.wu.deepinjvm.chap3;

/**
 * @author wuping
 * @date 2020-06-15
 */

public class User {
    public int id;
    public String name;
    public byte[] arr;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
        arr = new byte[1024 * 925 * 7];
    }

    @Override
    public String toString() {
        return "id: " + id + " ,name: " + name;
    }
}
