package com.ping.wu.refactor.chap1.version1;

import lombok.Data;

/**
 * @author wuping
 * @date 2020-09-28
 */

@Data
public class Movie {
    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;

    private String title;
    private int priceCode;

    public Movie(String title, int priceCode) {
        this.title = title;
        this.priceCode = priceCode;
    }
}
