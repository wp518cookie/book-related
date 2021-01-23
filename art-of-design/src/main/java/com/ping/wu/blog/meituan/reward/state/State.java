package com.ping.wu.blog.meituan.reward.state;

/**
 * @author wuping
 * @date 2021-01-12
 */

public abstract class State {
    Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    public abstract void handle1();

    public abstract void handle2();
}
