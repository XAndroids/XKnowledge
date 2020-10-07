package com.java.xknowledge.design.action.observer.enjoy;

/**
 * 观察者实现类
 */
class CustomObserver implements Observer {
    private String name;

    public CustomObserver(String name) {
        this.name = name;
    }

    @Override
    public void update() {
        System.out.println(name + "购买青芒");
    }
}
