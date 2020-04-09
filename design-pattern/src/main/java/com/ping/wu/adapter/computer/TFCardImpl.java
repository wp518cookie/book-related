package com.ping.wu.adapter.computer;

/**
 * @author wuping
 * @date 2019-10-01
 */

public class TFCardImpl implements TFCard {
    @Override
    public String readTF() {
        return "read from TF: hello tf";
    }

    @Override
    public int writeTF(String msg) {
        System.out.println("write into TF: " + msg);
        return 1;
    }
}
