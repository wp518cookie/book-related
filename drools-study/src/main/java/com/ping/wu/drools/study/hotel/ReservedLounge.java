package com.ping.wu.drools.study.hotel;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wuping
 * @date 2019-11-20
 */

@Data
public class ReservedLounge implements Serializable {
    static final long serialVersionUID = 1L;
    @org.kie.api.definition.type.Label("产品编码")
    private java.lang.String proCode;
    @org.kie.api.definition.type.Label("产品名称")
    private java.lang.String proName;
    @org.kie.api.definition.type.Label("位置")
    private Location location;
    @org.kie.api.definition.type.Label("是否自营")
    private boolean selfSupport;
}
