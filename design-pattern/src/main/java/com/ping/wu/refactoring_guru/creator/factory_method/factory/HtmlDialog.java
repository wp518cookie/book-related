package com.ping.wu.refactoring_guru.creator.factory_method.factory;

import com.ping.wu.refactoring_guru.creator.factory_method.buttons.Button;
import com.ping.wu.refactoring_guru.creator.factory_method.buttons.HtmlButton;

/**
 * @author wuping
 * @date 2020-11-02
 */

public class HtmlDialog extends Dialog {
    @Override
    public Button createButton() {
        return new HtmlButton();
    }
}
