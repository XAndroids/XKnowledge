package com.android.xknowledge.optimize.anr;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Debug;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.os.SystemClock;
import android.util.Log;

/**
 * ANR监控，WatchDog方案
 * 参考：享学2《性能优化-ANR优化》
 */
public class ANRWatchDog extends Thread {
    private static final String TAG = "ANR";
    //检测ANR响应实践为5秒
    private int timeout = 5000;
    //Debug是否忽略
    private boolean ignoreDebugger = true;

    //主线程Handler，用于post ANR Runnable，如果在timeout内执行完毕，则主线程没有阻塞
    private Handler mainHandler = new Handler(Looper.getMainLooper());
    //ANR检测Runnable，post到主线程Looper执行
    private ANRChecker anrChecker = new ANRChecker();

    //ANR监听回调
    private ANRListener anrListener;

    //单例
    static ANRWatchDog sWatchdog;

    public static ANRWatchDog getInstance() {
        if (sWatchdog == null) {
            sWatchdog = new ANRWatchDog();
        }
        return sWatchdog;
    }

    public void addANRListener(ANRListener listener) {
        this.anrListener = listener;
    }

    private ANRWatchDog() {
        //设置ANRWatchDog线程名称
        super("ANR-WatchDog-Thread");
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void run() {
        Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND); // 设置为后台线程
        while (true) {//Watchdog runnable无限循环检测
            while (!isInterrupted()) {
                synchronized (this) {
                    anrChecker.schedule();//将anrChecker post到主线程
                    long waitTime = timeout;
                    long start = SystemClock.uptimeMillis();
                    while (waitTime > 0) {
                        try {
                            wait(waitTime);//等待5秒后
                        } catch (InterruptedException e) {
                            Log.w(TAG, e.toString());
                        }
                        waitTime = timeout - (SystemClock.uptimeMillis() - start);
                    }
                    if (!anrChecker.isBlocked()) {//判断anrChecker是否执行，如果没有执行则出现ANR
                        continue;
                    }
                }
                if (!ignoreDebugger && Debug.isDebuggerConnected()) {
                    continue;
                }
                //出现ANR回调，获取堆栈信息回调
                String stackTraceInfo = getStackTraceInfo();
                if (anrListener != null) {
                    anrListener.onAnrHappened(stackTraceInfo);
                }
            }
            anrListener = null;
        }
    }

    private String getStackTraceInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        for (StackTraceElement stackTraceElement : Looper.getMainLooper().getThread().getStackTrace()) {
            stringBuilder
                    .append(stackTraceElement.toString())
                    .append("\r\n");
        }
        return stringBuilder.toString();
    }

    /**
     * ANR检测任务：将ANRChecker post到主线程消息队列，如果5秒内执行完了，则没有ANR；如果5秒内执行完，则有ANR
     */
    private class ANRChecker implements Runnable {

        private boolean mCompleted;
        private long mStartTime;
        private long executeTime = SystemClock.uptimeMillis();

        @Override
        public void run() {
            synchronized (ANRWatchDog.this) {
                //如果执行了ANRChecker，则这是为完成
                mCompleted = true;
                executeTime = SystemClock.uptimeMillis();
            }
        }

        /**
         * Post之前记录mStartTime和mCompleted
         */
        void schedule() {
            mCompleted = false;
            mStartTime = SystemClock.uptimeMillis();
            //将ANRChecker post到主线程消息队列
            mainHandler.postAtFrontOfQueue(this);
        }

        boolean isBlocked() {
            return !mCompleted || executeTime - mStartTime >= 5000;
        }
    }

    /**
     * ANR检测回调
     */
    public interface ANRListener {
        void onAnrHappened(String stackTraceInfo);
    }
}
