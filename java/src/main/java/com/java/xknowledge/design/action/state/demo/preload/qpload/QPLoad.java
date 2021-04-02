package com.java.xknowledge.design.action.state.demo.preload.qpload;

import com.java.xknowledge.design.action.state.demo.preload.QPLoadManager;

import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * QP加载类
 */
public class QPLoad implements Runnable {
    private String mHybridId;
    private DataLoader mDataLoader;
    //QP加载字符串
    private String mQPString;
    //QP加载状态，默认为初始化状态
    private IState mLoadState = new StateInit(this);
    private List<LoadCallback> mLoadCallbackList = new LinkedList<>();

    public QPLoad(String hybridId, DataLoader dataLoader) {
        mHybridId = hybridId;
        mDataLoader = dataLoader;
    }

    public void setLoadState(IState loadState) {
        mLoadState = loadState;
    }

    public String getmHybridId() {
        return mHybridId;
    }

    public void startLoad() {
        System.out.println("QPLoad_startLoad, mHybridId = " + mHybridId + "mLoadState = " + mLoadState);
        mLoadState.startLoad();//只能是StateInit状态
    }

    public void listenLoad(LoadCallback loadCallback) {
        System.out.println("QPLoad_listenLoad, mHybridId = " + mHybridId + "mLoadState = " + mLoadState);
        mLoadState.listenLoad(loadCallback);//可能是StateLoading或StateListening或StateComplete状态
    }

    void doStartLoad() {
        QPLoadManager.getInstance().mExecutorService.execute(this);
        setLoadState(new StateLoading(this));
    }

    @Override
    public void run() {
        System.out.println("QPLoad_run, mHybridId = " + mHybridId + "mLoadState = " + mLoadState);
        if (mDataLoader != null) {
            mQPString = mDataLoader.getQPDataString();
            mLoadState.loadComplete();//可能是StateListening或loadComplete状态
            System.out.println("QPLoad_run_mDataLoader.getQPDataString, mHybridId = " + mHybridId + "mLoadState = " + mLoadState + "mQPString = " + mQPString);
        }
    }

    void doQPLoadComplete() {
        System.out.println("QPLoad_doQPLoadComplete, mHybridId = " + mHybridId + "mLoadState = " + mLoadState);
        setLoadState(new StateComplete(this));
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("QPLoad_startTimeout, mHybridId = " + mHybridId + "mLoadState = " + mLoadState);
                mLoadState.timeOut();
            }
        }, 10 * 1000);
    }

    void setListeningAndAddLocalCallback(LoadCallback loadCallback) {
        addLoadCallback(loadCallback);
        setLoadState(new StateListening(this));
        System.out.println("QPLoad_setListeningAndAddLocalCallback, mHybridId = " + mHybridId + "mLoadState = " + mLoadState);
    }

    void addLoadCallback(LoadCallback loadCallback) {
        System.out.println("QPLoad_addLoadCallback, mHybridId = " + mHybridId + "mLoadState = " + mLoadState);
        mLoadCallbackList.add(loadCallback);
    }

    void addAndNotifyLoadCallback(LoadCallback loadCallback) {
        System.out.println("QPLoad_addAndNotifyLoadCallback, mHybridId = " + mHybridId + "mLoadState = " + mLoadState);
        mLoadCallbackList.add(loadCallback);
        notifyAllLoadCallback();
    }

    void notifyAllLoadCallback() {
        System.out.println("QPLoad_notifyAllLoadCallback, mHybridId = " + mHybridId + "mLoadState = " + mLoadState);
        if (!mLoadCallbackList.isEmpty()) {
            for (int i = 0; i < mLoadCallbackList.size(); i++) {
                mLoadCallbackList.get(i).onResult(mQPString);
            }
            setLoadState(new StateDone(this));
            QPLoadManager.getInstance().removeQPLoad(mHybridId);
        }
    }

    void removeQPLoad() {
        System.out.println("QPLoad_removeQPLoad, mHybridId = " + mHybridId + "mLoadState = " + mLoadState);
        setLoadState(new StateDestroy(this));
        QPLoadManager.getInstance().removeQPLoad(mHybridId);
    }
}
