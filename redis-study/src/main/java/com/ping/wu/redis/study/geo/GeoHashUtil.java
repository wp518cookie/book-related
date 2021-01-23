package com.ping.wu.redis.study.geo;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author wuping
 * @date 2020-09-17
 */

public class GeoHashUtil {
    public static void main(String[] args) {
        System.out.println(getGeoHash(new BigDecimal("116.37")));
    }

    public static String getGeoHash(BigDecimal location) {
        BigDecimal from = new BigDecimal(-180);
        BigDecimal to = new BigDecimal(180);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            BigDecimal mid = (from.add(to)).divide(new BigDecimal(2), 10, RoundingMode.HALF_UP);
            if (location.compareTo(mid) < 0) {
                sb.append("0");
                to = mid;
            } else {
                sb.append("1");
                from = mid;
            }
        }
        return sb.toString();
    }
}
