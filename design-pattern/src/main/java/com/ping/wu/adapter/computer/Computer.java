package com.ping.wu.adapter.computer;

/**
 * @author wuping
 * @date 2019-10-01
 */

public interface Computer {
    String readSD(SDCard sdCard);

    int writeSD(SDCard sdCard, String msg);
}
