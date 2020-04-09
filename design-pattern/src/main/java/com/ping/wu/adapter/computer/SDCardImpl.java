package com.ping.wu.adapter.computer;

/**
 * @author wuping
 * @date 2019-10-01
 */

public class SDCardImpl implements SDCard {
    @Override
    public String readSD() {
        return "read from SD card: hello sd";
    }

    @Override
    public int writeSD(String msg) {
        System.out.println("write to SD card: " + msg);
        return 1;
    }
}
