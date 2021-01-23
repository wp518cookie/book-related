package com.ping.wu.refactoring_guru.creator.abstract_factory.buttons;

/**
 * @author wuping
 * @date 2020-11-04
 */

public class MacOSButton implements Button {

    @Override
    public void paint() {
        System.out.println("You have created MacOSButton");
    }
}
