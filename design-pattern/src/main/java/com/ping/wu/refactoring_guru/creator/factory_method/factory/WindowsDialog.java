package com.ping.wu.refactoring_guru.creator.factory_method.factory;

import com.ping.wu.refactoring_guru.creator.factory_method.buttons.Button;
import com.ping.wu.refactoring_guru.creator.factory_method.buttons.WindowsButton;

/**
 * @author wuping
 * @date 2020-11-02
 */

public class WindowsDialog extends Dialog {
    @Override
    public Button createButton() {
        return new WindowsButton();
    }
}
