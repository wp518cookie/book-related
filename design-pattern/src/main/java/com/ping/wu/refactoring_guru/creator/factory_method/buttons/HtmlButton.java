package com.ping.wu.refactoring_guru.creator.factory_method.buttons;

/**
 * @author wuping
 * @date 2020-11-02
 */

public class HtmlButton implements Button {
    @Override
    public void render() {
        System.out.println("<button>Test Button</button>");
        onClick();
    }

    @Override
    public void onClick() {
        System.out.println("Click! Button says - 'Hello World!'");
    }
}
