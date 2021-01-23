package com.ping.wu.refactor.chap1.version1;

import lombok.Data;

/**
 * @author wuping
 * @date 2020-09-28
 */

@Data
public class Rental {
    private Movie movie;
    private int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }
}
