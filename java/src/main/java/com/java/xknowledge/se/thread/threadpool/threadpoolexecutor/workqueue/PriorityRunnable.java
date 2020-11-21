package com.java.xknowledge.se.thread.threadpool.threadpoolexecutor.workqueue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 优先级Runnable
 */
class PriorityRunnable implements Runnable, Comparable<PriorityRunnable> {
    //优先级
    private int priority;
    private int simulateTime;
    private SimpleDateFormat simpleDateFormat;

    {
        simpleDateFormat = new SimpleDateFormat("hh:mm:ss", Locale.CHINA);
    }

    public PriorityRunnable(int priority) {
        this.priority = priority;
    }

    public PriorityRunnable(int priority, int simulateTime) {
        this(priority);
        this.simulateTime = simulateTime;
    }

    @Override
    public void run() {
        System.out.println("currentTime = " + simpleDateFormat.format(new Date()) + "，第 " + this.priority +
                " PriorityRunnable，使用线程" + Thread.currentThread().getName() + "开始执行....");
        try {
            Thread.sleep(simulateTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("currentTime = " + simpleDateFormat.format(new Date()) + "，第 " + this.priority +
                " PriorityRunnable，使用线程" + Thread.currentThread().getName() + "执行完毕----");
    }

    @Override
    public int compareTo(PriorityRunnable priorityRunnable) {
        if (this.priority == priorityRunnable.priority) {
            return 0;
        }
        return this.priority > priorityRunnable.priority ? -1 : 1;
    }
}
