package com.ping.wu.refactoring_guru.creator.abstract_factory.factory;

import com.ping.wu.refactoring_guru.creator.abstract_factory.buttons.Button;
import com.ping.wu.refactoring_guru.creator.abstract_factory.checkboxes.Checkbox;

/**
 * @author wuping
 * @date 2020-11-04
 */

public interface GUIFactory {
    Button createButton();

    Checkbox createCheckbox();
}
