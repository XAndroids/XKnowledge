package com.java.xknowledge.design.action.state.demo.preload.qpload;

/**
 * 初始化状态：QPLoad初始化，还未开始加载
 */
public class StateInit extends BaseState {

    public StateInit(QPLoad qPLoad) {
        super(qPLoad);
    }

    @Override
    public void startLoad() {
        System.out.println("StateInit_startLoad, hybrid = " + mQPLoad.getmHybridId());
        mQPLoad.doStartLoad();
    }
}
