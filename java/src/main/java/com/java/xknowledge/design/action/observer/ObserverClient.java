package com.java.xknowledge.design.action.observer;

/**
 * 观察者模式
 * 参考：参考：享学《设计模式-观察者模式》
 */
class ObserverClient {
    public static void main(String[] args) {
        MangoAttentions mangoAttentions = new MangoAttentions();
        //添加观察者
        mangoAttentions.add(new CustomObserver("Lilei"));
        mangoAttentions.add(new CustomObserver("Tom"));
        //芒果到货
        mangoAttentions.perform();
    }
}
