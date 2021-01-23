package com.ping.wu.refactor.chap1.version1;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wuping
 * @date 2020-09-28
 */

@Data
public class Customer {
    private String name;
    private List<Rental> rentals = new ArrayList();

    public Customer(String name) {
        this.name = name;
    }
}
