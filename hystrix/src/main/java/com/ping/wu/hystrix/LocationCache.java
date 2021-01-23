package com.ping.wu.hystrix;

import com.netflix.hystrix.HystrixCommand;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wuping
 * @date 2020-08-20
 */

public class LocationCache {
    private static Map<Long, String> cityMap = new HashMap();

    static {
        cityMap.put(1L, "上海");
        cityMap.put(2L, "北京");
    }

    public static String getCityName(Long cityId) {
        return cityMap.get(cityId);
    }


}
