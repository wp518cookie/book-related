package com.ping.wu.drools.study.hotel;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wuping
 * @date 2019-11-20
 */

@Data
public class Segment implements Serializable {
    static final long serialVersionUID = 1L;
    @org.kie.api.definition.type.Label("产品编码")
    private java.lang.String proCode;
    @org.kie.api.definition.type.Label("产品名称")
    private java.lang.String proName;
    @org.kie.api.definition.type.Label("出发城市")
    private java.lang.String startCity;
    @org.kie.api.definition.type.Label("到达城市")
    private java.lang.String arriveCity;
    @org.kie.api.definition.type.Label("舱位")
    private java.lang.String cabin;
    @org.kie.api.definition.type.Label("航班日期")
    private java.util.Date flightDate;
}
