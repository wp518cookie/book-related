package com.ping.wu.adapter.computer;

/**
 * @author wuping
 * @date 2019-10-01
 */

public interface TFCard {
    String readTF();

    int writeTF(String msg);
}
