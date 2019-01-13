package com.ping.wu.rpc.serialize;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @author wuping
 * @date 2019/1/2
 */

public class HelloKryo {
    public static void main(String[] args) throws Exception {
        Kryo kryo = new Kryo();
        kryo.register(Person.class);
        Person person = new Person();
        person.setName("wuping");
        person.setAge(30);
        person.setSex("male");
        Output output = new Output(new FileOutputStream("file.bin"));
        kryo.writeObject(output, person);
        output.close();
        Input input = new Input(new FileInputStream("file.bin"));
        Person person1 = kryo.readObject(input, Person.class);
        input.close();
        System.out.println(person1);
    }
}
