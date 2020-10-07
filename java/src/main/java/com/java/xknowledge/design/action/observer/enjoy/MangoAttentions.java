package com.java.xknowledge.design.action.observer.enjoy;

/**
 * 芒果被观察者
 */
class MangoAttentions extends Attentions {

    @Override
    public void notifyObservers() {
        for (Observer observer : observerList) {
            observer.update();
        }
    }

    public void perform() {
        //到货通知观察者
        notifyObservers();
    }
}
