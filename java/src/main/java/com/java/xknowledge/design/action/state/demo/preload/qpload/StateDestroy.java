package com.java.xknowledge.design.action.state.demo.preload.qpload;

/**
 * 加载销毁状态：数据加载完成，但超时还没有观察者监听
 */
class StateDestroy extends BaseState {

    public StateDestroy(QPLoad qPLoad) {
        super(qPLoad);
    }
}
