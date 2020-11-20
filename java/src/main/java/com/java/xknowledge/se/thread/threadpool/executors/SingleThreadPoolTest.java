package com.java.xknowledge.se.thread.threadpool.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Executors.newSingleThreadExecutor()，单个线程线程池测试类
 * newSingleThreadExecutor源码：new ThreadPoolExecutor(1, 1,0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>())
 * 按个线程线程池：核心线程数：corePoolSize = 1、最大线程数：maximumPoolSize = 1、非核心线程生命时间：keepAliveTime = 0L、阻塞队列：LinkedBlockingQueue
 *   1.只有一个核心线程，没有非核心线程；
 *   2.所有任务都在这个线程中执行，有任务正在执行，进入阻断队列等待执行，先进先出
 * //参考：https://juejin.cn/post/6844903825740922887
 */
class SingleThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor(new MyThreadFactory());
        //每个任务依次在线程中执行，没有被执行的任务放在等待去列中
        //currentTime = 1605866419459，第 0 Runnable，使用线程pool-1-thread-1开始执行....
        //currentTime = 1605866420459，第 0 Runnable，使用线程pool-1-thread-1执行完毕----
        //currentTime = 1605866420459，第 1 Runnable，使用线程pool-1-thread-1开始执行....
        //currentTime = 1605866421460，第 1 Runnable，使用线程pool-1-thread-1执行完毕----
        //currentTime = 1605866421460，第 2 Runnable，使用线程pool-1-thread-1开始执行....
        //currentTime = 1605866422462，第 2 Runnable，使用线程pool-1-thread-1执行完毕----
        //currentTime = 1605866422462，第 3 Runnable，使用线程pool-1-thread-1开始执行....
        //currentTime = 1605866423463，第 3 Runnable，使用线程pool-1-thread-1执行完毕----
        //currentTime = 1605866423463，第 4 Runnable，使用线程pool-1-thread-1开始执行....
        //currentTime = 1605866424467，第 4 Runnable，使用线程pool-1-thread-1执行完毕----
        //currentTime = 1605866424467，第 5 Runnable，使用线程pool-1-thread-1开始执行....
        //currentTime = 1605866425467，第 5 Runnable，使用线程pool-1-thread-1执行完毕----
        //currentTime = 1605866425467，第 6 Runnable，使用线程pool-1-thread-1开始执行....
        //currentTime = 1605866426471，第 6 Runnable，使用线程pool-1-thread-1执行完毕----
        //currentTime = 1605866426471，第 7 Runnable，使用线程pool-1-thread-1开始执行....
        for (int i = 0; i < 60; i++) {
            singleThreadExecutor.execute(new MyRunnable(i, 1000));
        }
    }
}
