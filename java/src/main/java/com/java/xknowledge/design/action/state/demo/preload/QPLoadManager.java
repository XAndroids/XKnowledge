package com.java.xknowledge.design.action.state.demo.preload;

import com.java.xknowledge.design.action.state.demo.preload.qpload.DataLoader;
import com.java.xknowledge.design.action.state.demo.preload.qpload.LoadCallback;
import com.java.xknowledge.design.action.state.demo.preload.qpload.QPLoad;
import com.java.xknowledge.se.thread.threadpool.MyThreadFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * QP加载管理类：负责QP加载任务集合管理，线程池维护
 */
public class QPLoadManager {
    public ExecutorService mExecutorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
            10L, TimeUnit.SECONDS, new SynchronousQueue<>(), new MyThreadFactory());
    private Map<String, QPLoad> mQpLoadMap = new ConcurrentHashMap<>();

    private QPLoadManager() {

    }

    static class Holder {
        private static final QPLoadManager INSTANCE = new QPLoadManager();
    }

    public static QPLoadManager getInstance() {
        return Holder.INSTANCE;
    }

    public void removeQPLoad(String hybirdId) {
        mQpLoadMap.remove(hybirdId);
    }

    void loadQP(String hybrid, DataLoader dataLoader) {
        System.out.println("QPLoadManager_loadQP, hybrid = " + hybrid + ",dataLoader = " + dataLoader);
        QPLoad qpLoad = mQpLoadMap.get(hybrid);
        if (qpLoad == null) {
            System.out.println("QPLoadManager_loadQP_new QPLoad, hybrid = " + hybrid);
            qpLoad = new QPLoad(hybrid, dataLoader);
            mQpLoadMap.put(hybrid, qpLoad);
            System.out.println("QPLoadManager_loadQP_mQpLoadMap.put, mQpLoadMap = " +
                    mQpLoadMap.toString());
            qpLoad.startLoad();
        }
    }

    void listenQP(String hybrid, LoadCallback loadCallback) {
        System.out.println("QPLoadManager_listenQP, hybrid = " + hybrid + ",loadCallback = " +
                loadCallback);
        QPLoad qpLoad = mQpLoadMap.get(hybrid);
        if (qpLoad != null && loadCallback != null)
            qpLoad.listenLoad(loadCallback);
    }
}