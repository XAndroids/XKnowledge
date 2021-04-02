package com.java.xknowledge.design.action.state.demo.preload.qpload;

/**
 * 加载完毕状态：QP加载完成，已通知QP观察者
 */
class StateDone extends BaseState {

    public StateDone(QPLoad qPLoad) {
        super(qPLoad);
    }
}
