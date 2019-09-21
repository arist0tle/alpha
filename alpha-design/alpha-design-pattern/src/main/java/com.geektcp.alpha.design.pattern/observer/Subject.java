package com.geektcp.alpha.design.pattern.observer;

/**
 * Created by TangHaiyang on 2019/9/21.
 */
public interface Subject {
    void registerObserver(Observer o);

    void removeObserver(Observer o);

    void notifyObserver();
}
