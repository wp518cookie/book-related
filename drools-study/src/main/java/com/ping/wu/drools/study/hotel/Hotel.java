package com.ping.wu.drools.study.hotel;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wuping
 * @date 2019-11-20
 */

@Data
public class Hotel implements Serializable {
    static final long serialVersionUID = 1L;
    @org.kie.api.definition.type.Label("产品编码")
    private java.lang.String proCode;
    @org.kie.api.definition.type.Label("产品名称")
    private java.lang.String proName;
    @org.kie.api.definition.type.Label("房型")
    private java.lang.String roomType;
    @org.kie.api.definition.type.Label("入住日期")
    private java.util.Date checkInDate;
    @org.kie.api.definition.type.Label("位置")
    private Location location;
    @org.kie.api.definition.type.Label(value = "\u662F\u5426\u53EF\u6253\u5305\u9500\u552E")
    private java.lang.Boolean ifCanPackageSale;
}
