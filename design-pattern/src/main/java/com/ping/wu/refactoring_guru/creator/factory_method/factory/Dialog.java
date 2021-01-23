package com.ping.wu.refactoring_guru.creator.factory_method.factory;

import com.ping.wu.refactoring_guru.creator.factory_method.buttons.Button;

/**
 * @author wuping
 * @date 2020-11-02
 */

public abstract class Dialog {
    public void renderWindow() {
        Button okButton = createButton();
        okButton.render();
    }

    public abstract Button createButton();
}
