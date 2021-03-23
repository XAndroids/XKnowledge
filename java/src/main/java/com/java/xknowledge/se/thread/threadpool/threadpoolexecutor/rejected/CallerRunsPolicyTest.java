package com.java.xknowledge.se.thread.threadpool.threadpoolexecutor.rejected;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal拒绝策略：CallerRunsPolicy，
 */
public class CallerRunsPolicyTest {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 2,
                0, TimeUnit.MICROSECONDS, new LinkedBlockingDeque<>(2),
                new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 0; i < 6; i++) {
            System.out.println("添加第" + i + "个任务");
            executor.execute(new MyThread("线程" + i));
            for (Runnable runnable : executor.getQueue()) {
                MyThread thread = (MyThread) runnable;
                System.out.println("列表:" + thread.name);
            }
        }
    }
}
