package com.java.xknowledge.se.thread.threadpool.executors;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池工厂，主要用于新建线程的命名
 * 参考：https://blog.csdn.net/liuxiao723846/article/details/79667432
 */
class MyThreadFactory implements ThreadFactory {
    //线程池的索引，用于唯一标识线程池
    private static final AtomicInteger poolIndex = new AtomicInteger(1);
    //线程的索引，用于唯一标识线程
    private final AtomicInteger threadIndex = new AtomicInteger(1);
    private final String namePrefix;

    public MyThreadFactory() {
        namePrefix = "pool-" + poolIndex.getAndIncrement() + "-thread-";
    }

    @Override
    public Thread newThread(Runnable runnable) {
        return new Thread(runnable, namePrefix + threadIndex.getAndIncrement());
    }
}
