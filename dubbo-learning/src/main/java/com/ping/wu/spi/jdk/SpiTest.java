package com.ping.wu.spi.jdk;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.ServiceLoader;

/**
 * @author wuping
 * @date 2019-05-28
 */

public class SpiTest {
    public static void main(String[] args) throws Exception {
//        LinkedHashMap<String,S> providers
        ServiceLoader<IShout> shouts = ServiceLoader.load(IShout.class);
        Field field = shouts.getClass().getDeclaredField("providers");
        field.setAccessible(true);
        LinkedHashMap linkedHashMap = (LinkedHashMap) field.get(shouts);
        System.out.println("linkedHashMap:" + linkedHashMap.size());
        for (IShout s : shouts) {
            s.shout();
        }
        System.out.println(Male.class);
        System.out.println(Class.forName("com.ping.wu.spi.jdk.Male"));
    }
}
