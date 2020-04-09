package com.ping.wu.observer;

import java.util.Objects;

/**
 * @author wuping
 * @date 2019-09-25
 */

public class SimpleObserver implements Observer {
    private int val = 0;

    @Override
    public void update(int val) {
        this.val = val;
        this.display();
    }

    public void display() {
        System.out.println("simple observer:" + val);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(val);
    }
}
