package com.java.xknowledge.design.action.state.demo.preload.qpload;

/**
 * 任务加载状态基类，声明不同任务支持的操作
 */
public interface IState {
    //开始加载任务
    void startLoad();

    //完成加载任务
    void loadComplete();

    //监听加载任务
    void listenLoad(LoadCallback loadCallback);

    //加载任务超时
    void timeOut();
}
