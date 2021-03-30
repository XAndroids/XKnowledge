package com.java.xknowledge.se.thread.demo.qpcache;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * QP加载Runnable
 */
public class QPLoadRunnable implements Runnable {
    private final int mI;
    private final SimpleDateFormat simpleDateFormat;

    {
        simpleDateFormat = new SimpleDateFormat("hh:mm:ss", Locale.CHINA);
    }

    public QPLoadRunnable(int i) {
        mI = i;
    }

    @Override
    public void run() {
        try {
            System.out.println("currentTime = " + simpleDateFormat.format(new Date()) + "，第 " + this.mI +
                    " Runnable，使用线程" + Thread.currentThread().getName() + "开始写入....");

            //更新mI QP开始加载
            QPCacheManager.getInstance().getTest(mI).setLoadState(QPCache.START_LOADING);
            Thread.sleep(3000);//模拟IO操作
            String qpString = mI + "QPString";
            QPCacheManager.getInstance().putTestQPString(mI, qpString);

            System.out.println("currentTime = " + simpleDateFormat.format(new Date()) + "，第 " + this.mI +
                    " Runnable，使用线程" + Thread.currentThread().getName() + "写入完毕----" + qpString);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
