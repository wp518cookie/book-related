package com.ping.wu.jvm.bytecode;

/**
 * @author wuping
 * @date 2019-04-15
 */

public class DynamicDispatch {
    static class Human {
        protected void sayHello() {
            System.out.println("Human say hello");
        }
    }

    static class Man extends Human {
        @Override
        protected void sayHello() {
            System.out.println("man say hello");
        }
    }

    static class Woman extends Human {
        @Override
        protected void sayHello() {
            System.out.println("woman say hello");
        }
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        man.sayHello();
        woman.sayHello();
        man = new Woman();
        man.sayHello();
    }
}
