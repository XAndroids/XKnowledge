package com.java.xknowledge.design.action.observer.enjoy;
import java.util.ArrayList;
import java.util.List;
/**
 * 被观察者抽象类
 */
abstract class Attentions {
    //观察者列表
    List<Observer> observerList = new ArrayList<>();

    /**
     * 增加观察扎
     */
    void add(Observer observer) {
        observerList.add(observer);
    }

    /**
     * 删除观察者
     */
    void remove(Observer observer) {
        observerList.remove(observer);
    }

    /**
     * 通知观察者
     */
    public abstract void notifyObservers();
}
