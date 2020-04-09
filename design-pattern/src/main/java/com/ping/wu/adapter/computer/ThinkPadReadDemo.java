package com.ping.wu.adapter.computer;

/**
 * @author wuping
 * @date 2019-10-01
 */

public class ThinkPadReadDemo {
    public static void main(String[] args) {
        ThinkPad thinkPad = new ThinkPad();
        TFCard tfCard = new TFCardImpl();
        SDCard sdCard = new TFCardAdapter(tfCard);
        System.out.println(thinkPad.readSD(sdCard));
        thinkPad.writeSD(sdCard, "123");
    }
}
