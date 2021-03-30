package com.java.xknowledge.se.thread.demo.qpcache;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

class QPCache {
    public static final int NO_LOAD = -1;
    public static final int START_LOADING = 0;
    public static final int LOADED_DOWN = 2;

    private String mQPString;
    private volatile int mLoadState = NO_LOAD;
    private final SimpleDateFormat simpleDateFormat;

    {
        simpleDateFormat = new SimpleDateFormat("hh:mm:ss", Locale.CHINA);
    }


    public QPCache() {
    }

    public void setLoadState(int loadState) {
        mLoadState = loadState;
    }

    public synchronized String getQPString() {
        try {
            //如果当前QP还没有开始加载或者还未加载完成，则wait等待
            if (mLoadState == START_LOADING || mLoadState == NO_LOAD) {
                System.out.println("currentTime = " + simpleDateFormat.format(new Date())
                        + "，" + Thread.currentThread().getName() + "读取wait()....");
                wait();
                System.out.println("currentTime = " + simpleDateFormat.format(new Date())
                        + "，" + Thread.currentThread().getName() + "读取唤醒....");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mQPString;
    }

    /**
     * wait()方法要使用在synchronized修饰的方法里面要不然会报异常
     * Exception in thread "pool-1-thread-21" Exception in thread "pool-1-thread-22" java.lang.IllegalMonitorStateException
     * at java.lang.Object.wait(Native Method)
     * at java.lang.Object.wait(Object.java:502)
     * at com.java.xknowledge.se.thread.demo.qpcache.TestCache.getTestQPString(TestCache.java:40)
     * at com.java.xknowledge.se.thread.demo.qpcache.ReadRunnable.run(ReadRunnable.java:24)
     * at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
     * at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
     * at java.lang.Thread.run(Thread.java:748)
     * 参考：https://juejin.cn/post/6844903503979102216
     */
    public synchronized void setQPString(String qPString) {
        mQPString = qPString;
        mLoadState = LOADED_DOWN;

        System.out.println("currentTime = " + simpleDateFormat.format(new Date())
                + "，" + Thread.currentThread().getName() + "写入完成....");

        //如果当前QP加载完成了，则通知等待的获取QP线程唤醒，读取QP
        notify();
    }
}
