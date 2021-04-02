package com.java.xknowledge.design.action.state.demo.preload.qpload;

/**
 * 监听状态：QP正在加载且有观察者监听
 */
public class StateListening extends BaseState {
    public StateListening(QPLoad qPLoad) {
        super(qPLoad);
    }

    @Override
    public void loadComplete() {
        System.out.println("StateListening_loadComplete, hybrid = " + mQPLoad.getmHybridId());
        mQPLoad.notifyAllLoadCallback();
    }

    @Override
    public void listenLoad(LoadCallback loadCallback) {
        System.out.println("StateListening_listenLoad, hybrid = " + mQPLoad.getmHybridId());
        mQPLoad.addLoadCallback(loadCallback);
    }
}
