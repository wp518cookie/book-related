package com.ping.wu.refactoring_guru.creator.abstract_factory.factory;

import com.ping.wu.refactoring_guru.creator.abstract_factory.buttons.Button;
import com.ping.wu.refactoring_guru.creator.abstract_factory.buttons.WindowsButton;
import com.ping.wu.refactoring_guru.creator.abstract_factory.checkboxes.Checkbox;
import com.ping.wu.refactoring_guru.creator.abstract_factory.checkboxes.WindowsCheckbox;

/**
 * @author wuping
 * @date 2020-11-04
 */

public class WindowsFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }
}
