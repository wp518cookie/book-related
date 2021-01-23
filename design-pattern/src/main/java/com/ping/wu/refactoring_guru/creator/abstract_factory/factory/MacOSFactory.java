package com.ping.wu.refactoring_guru.creator.abstract_factory.factory;

import com.ping.wu.refactoring_guru.creator.abstract_factory.buttons.Button;
import com.ping.wu.refactoring_guru.creator.abstract_factory.buttons.MacOSButton;
import com.ping.wu.refactoring_guru.creator.abstract_factory.checkboxes.Checkbox;
import com.ping.wu.refactoring_guru.creator.abstract_factory.checkboxes.MacOSCheckbox;

/**
 * @author wuping
 * @date 2020-11-04
 */

public class MacOSFactory implements GUIFactory {

    @Override
    public Button createButton() {
        return new MacOSButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new MacOSCheckbox();
    }
}
