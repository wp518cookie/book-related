package com.ping.wu.algo.other.redenvelope;

/**
 * @author wuping
 * @date 2019-10-30
 */

public class Redpackage {
    int remainSize;
    int remainMoney;

    public Redpackage() {

    }

    public Redpackage(int remainSize, int remainMoney) {
        this.remainSize = remainSize;
        this.remainMoney = remainMoney;
    }

    public int getRemainSize() {
        return remainSize;
    }

    public void setRemainSize(int remainSize) {
        this.remainSize = remainSize;
    }

    public int getRemainMoney() {
        return remainMoney;
    }

    public void setRemainMoney(int remainMoney) {
        this.remainMoney = remainMoney;
    }
}
