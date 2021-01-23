package com.ping.wu.refactoring_guru.creator.factory_method;

import com.ping.wu.refactoring_guru.creator.factory_method.factory.Dialog;
import com.ping.wu.refactoring_guru.creator.factory_method.factory.HtmlDialog;
import com.ping.wu.refactoring_guru.creator.factory_method.factory.WindowsDialog;

/**
 * @author wuping
 * @date 2020-11-02
 */

public class Demo {
    private static Dialog dialog;

    public static void main(String[] args) {
        configure();
        runBusinessLogic();
    }

    static void configure() {
        if (System.getProperty("os.name").equals("Windows 10")) {
            dialog = new WindowsDialog();
        } else {
            dialog = new HtmlDialog();
        }
    }

    static void runBusinessLogic() {
        dialog.renderWindow();
    }
}
