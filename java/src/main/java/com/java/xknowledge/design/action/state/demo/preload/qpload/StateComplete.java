package com.java.xknowledge.design.action.state.demo.preload.qpload;

/**
 * 加载完成状态：但还没有观察者，等待观察者
 */
class StateComplete extends BaseState {
    public StateComplete(QPLoad qPLoad) {
        super(qPLoad);
    }

    @Override
    public void listenLoad(LoadCallback loadCallback) {
        System.out.println("StateComplete_listenLoad, hybrid = " + mQPLoad.getmHybridId());
        mQPLoad.addAndNotifyLoadCallback(loadCallback);
    }

    @Override
    public void timeOut() {
        System.out.println("StateComplete_timeOut, hybrid = " + mQPLoad.getmHybridId());
        mQPLoad.removeQPLoad();
    }
}
