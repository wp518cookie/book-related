package com.ping.wu.observer;

/**
 * @author wuping
 * @date 2019-09-25
 */

public interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObserver(int val);
}
