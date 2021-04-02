package com.java.xknowledge.design.action.state.demo.preload.qpload;

/**
 * 正在加载状态：QP正在加载
 */
public class StateLoading extends BaseState {
    public StateLoading(QPLoad qPLoad) {
        super(qPLoad);
    }

    @Override
    public void loadComplete() {
        System.out.println("StateLoading_loadComplete, hybrid = " + mQPLoad.getmHybridId());
        mQPLoad.doQPLoadComplete();
    }

    @Override
    public void listenLoad(LoadCallback loadCallback) {
        System.out.println("StateLoading_listenLoad, hybrid = " + mQPLoad.getmHybridId());
        mQPLoad.setListeningAndAddLocalCallback(loadCallback);
    }
}
