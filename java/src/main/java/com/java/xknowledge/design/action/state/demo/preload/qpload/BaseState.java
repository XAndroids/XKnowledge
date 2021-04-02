package com.java.xknowledge.design.action.state.demo.preload.qpload;

/**
 * 状态基类，实现接口，避免所有状态实现必须实现所有方法，包含QPLoad公共成员
 */
abstract class BaseState implements IState {
    //QP加载任务
    QPLoad mQPLoad;

    public BaseState(QPLoad qPLoad) {
        mQPLoad = qPLoad;
    }

    @Override
    public void startLoad() {

    }

    @Override
    public void loadComplete() {

    }

    @Override
    public void listenLoad(LoadCallback loadCallback) {

    }

    @Override
    public void timeOut() {

    }
}
