package com.ping.wu.adapter.computer;

/**
 * @author wuping
 * @date 2019-10-01
 */

public class ThinkPad implements Computer {

    @Override
    public String readSD(SDCard sdCard) {
        return sdCard.readSD();
    }

    @Override
    public int writeSD(SDCard sdCard, String msg) {
        return sdCard.writeSD(msg);
    }
}
