package com.java.xknowledge.se.thread.threadpool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.Callable;

/**
 * Callable和Runnable的区别:
 * 1.Runnable是从Java 1.0就有，而Callable是1.5之后才加上去的
 * 2.Callable规定的方法是call()，Runnable规定是的run()
 * 3.Callable任务执行后返回值，而Runnable任务是不能返回值
 * 4.Callable可以抛出异常，run不可以
 * 5.运行Callable可以拿到一个Future对象，表示异步计算结果，检查计算是否完成，以等待计算结果，可取消任务执行
 * 6.加入线程池，Runnable使用ExecutorService的execute()方法，Callable使用submit()方法
 * 参考：https://blog.csdn.net/qfikh/article/details/77645054
 */
public class MyCallable implements Callable<String> {
    private int i;
    private int simulateTime;
    private SimpleDateFormat simpleDateFormat;

    {
        simpleDateFormat = new SimpleDateFormat("hh:mm:ss", Locale.CHINA);
    }

    public MyCallable(int i) {
        this.i = i;
    }

    public MyCallable(int i, int simulateTime) {
        this(i);
        this.simulateTime = simulateTime;
    }

    @Override
    public String call() throws Exception {
        System.out.println("currentTime = " + simpleDateFormat.format(new Date()) + "，第 " + this.i +
                " Callable，使用线程" + Thread.currentThread().getName() + "开始执行....");
        try {
            Thread.sleep(simulateTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("currentTime = " + simpleDateFormat.format(new Date()) + "，第 " + this.i +
                " Callable，使用线程" + Thread.currentThread().getName() + "执行完毕----");
        return "success";
    }
}
