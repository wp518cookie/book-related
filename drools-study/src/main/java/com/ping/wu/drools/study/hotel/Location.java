package com.ping.wu.drools.study.hotel;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wuping
 * @date 2019-11-20
 */

@Data
public class Location implements Serializable {
    static final long serialVersionUID = 1L;
    // 国家
    @org.kie.api.definition.type.Label(value = "\u56FD\u5BB6")
    // 省份
    private java.lang.String country;
    @org.kie.api.definition.type.Label(value = "\u7701\u4EFD")
    private java.lang.String province;
    // 城市
    @org.kie.api.definition.type.Label(value = "\u57CE\u5E02")
    private java.lang.String city;

    public Location() {

    }

    public Location(String country, String province, String city) {
        this.country = country;
        this.province = province;
        this.city = city;
    }
}
