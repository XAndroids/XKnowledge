package com.java.xknowledge.se.thread.demo.qpcache;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * QP去读Runnable
 */
public class QPReadRunnable implements Runnable {
    private final int mI;
    private final SimpleDateFormat simpleDateFormat;

    {
        simpleDateFormat = new SimpleDateFormat("hh:mm:ss", Locale.CHINA);
    }

    public QPReadRunnable(int i) {
        mI = i;
    }

    @Override
    public void run() {
        System.out.println("currentTime = " + simpleDateFormat.format(new Date()) + "，第 " + this.mI +
                " Runnable，使用线程" + Thread.currentThread().getName() + "开始读取....");

        //异步读取指定mI Id的QP字符串
        String qpString = QPCacheManager.getInstance().getTestQPString(mI);

        System.out.println("currentTime = " + simpleDateFormat.format(new Date()) + "，第 " + this.mI +
                " Runnable，使用线程" + Thread.currentThread().getName() + "读取完毕----" + qpString);
    }
}
