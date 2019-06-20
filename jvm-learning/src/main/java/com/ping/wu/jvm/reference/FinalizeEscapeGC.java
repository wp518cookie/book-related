package com.ping.wu.jvm.reference;

import java.util.concurrent.TimeUnit;

/**
 * @author wuping
 * @date 2019/4/10
 */

public class FinalizeEscapeGC {
    public static FinalizeEscapeGC SAVE_HOOK = null;

    public void isAlive() {
        System.out.println("yes, i am still alive");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed!");
        /**
         * 重新与静态变量建立连接
         */
        FinalizeEscapeGC.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws Throwable {
        SAVE_HOOK = new FinalizeEscapeGC();
        SAVE_HOOK = null;
        System.gc();
        TimeUnit.MILLISECONDS.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no , i am dead :(");
        }
        SAVE_HOOK = null;
        System.gc();
        TimeUnit.MILLISECONDS.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no , i am dead :(");
        }
    }
}
