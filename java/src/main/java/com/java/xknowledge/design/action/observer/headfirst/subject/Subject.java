package com.java.xknowledge.design.action.observer.headfirst.subject;

import com.java.xknowledge.design.action.observer.headfirst.observer.Observer;

/**
 * 主题接口，声明了观察者注册、移除和通知方法
 */
public interface Subject {
    void registerObserver(Observer o);    //注册观察者

    void removeObserver(Observer o);    //注销观察者

    void notifyObservers();    //通知观察者
}
