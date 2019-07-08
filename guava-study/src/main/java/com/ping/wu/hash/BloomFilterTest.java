package com.ping.wu.hash;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.lang.reflect.Method;
import java.nio.charset.Charset;

/**
 * @author wuping
 * @date 2019-06-28
 */

public class BloomFilterTest {
    public static void main(String[] args) throws Exception {
        BloomFilter<Long> bloomFilter = BloomFilter.create(Funnels.longFunnel(), 1000000, 0.00001);
        Method method = bloomFilter.getClass().getDeclaredMethod("bitSize", null);
        method.setAccessible(true);
        long val = Long.valueOf(method.invoke(bloomFilter, null).toString()) / 64;
        long val1 = 1000000;
        System.out.println(val);
        System.out.println(val1);
        System.out.println(val1 / 8 / 1024);
    }
}
