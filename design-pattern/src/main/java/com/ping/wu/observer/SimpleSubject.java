package com.ping.wu.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wuping
 * @date 2019-09-25
 */

public class SimpleSubject implements Subject {
    private List<Observer> observers = new ArrayList();

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObserver(int val) {
        for (Observer observer : observers) {
            observer.update(val);
        }
    }

    public void setVal(int updated) {
        notifyObserver(updated);
    }
}
