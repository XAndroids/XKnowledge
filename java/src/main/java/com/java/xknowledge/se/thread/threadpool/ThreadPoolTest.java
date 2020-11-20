package com.java.xknowledge.se.thread.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池测试实践
 */
class ThreadPoolTest {
    private static ThreadPoolExecutor threadPoolExecutor;

    public static void main(String[] args) {
        int corePoolSize = Runtime.getRuntime().availableProcessors() + 1;
        int maximumPoolSize = Runtime.getRuntime().availableProcessors() * 2;
        System.out.println("corePoolSize = " + corePoolSize + ",maximumPoolSize = " + maximumPoolSize);

        //无界阻塞队列：当前无法执行的任务，在队列中等待，不会Rejected处理
        BlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<>();
        ThreadFactory threadFactory = new ThreadFactory() {
            private final AtomicInteger mCount = new AtomicInteger(1);

            public Thread newThread(Runnable runnable) {
                return new Thread(runnable, "ThreadPoolTest #" + this.mCount.getAndIncrement());
            }
        };
        threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, 10L, TimeUnit.SECONDS, blockingQueue, threadFactory);

        for (int i = 0; i < 100; i++) {
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Thread = " + Thread.currentThread().getName() + ", run...");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
