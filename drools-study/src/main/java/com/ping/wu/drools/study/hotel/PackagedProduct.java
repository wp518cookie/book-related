package com.ping.wu.drools.study.hotel;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wuping
 * @date 2019-11-20
 */

@Data
public class PackagedProduct implements Serializable {
    static final long serialVersionUID = 1L;
    @org.kie.api.definition.type.Label(value = "\u6210\u5458\u4EA7\u54C1ID\u5217\u8868")
    private java.util.List<java.lang.String> itemProductCodes;
}
