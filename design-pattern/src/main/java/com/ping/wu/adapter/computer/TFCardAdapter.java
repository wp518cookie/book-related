package com.ping.wu.adapter.computer;

/**
 * @author wuping
 * @date 2019-10-01
 */

public class TFCardAdapter implements SDCard {
    private TFCard tfCard;

    public TFCardAdapter(TFCard tfCard) {
        this.tfCard = tfCard;
    }

    @Override
    public String readSD() {
        return tfCard.readTF();
    }

    @Override
    public int writeSD(String msg) {
        return tfCard.writeTF(msg);
    }
}
