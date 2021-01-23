package com.ping.wu.refactoring_guru.creator.abstract_factory.checkboxes;

/**
 * @author wuping
 * @date 2020-11-04
 */

public class WindowsCheckbox implements Checkbox {
    @Override
    public void paint() {
        System.out.println("You have created WindowsCheckbox");
    }
}
