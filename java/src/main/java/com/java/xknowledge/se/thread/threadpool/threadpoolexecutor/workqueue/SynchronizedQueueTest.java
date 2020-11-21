package com.java.xknowledge.se.thread.threadpool.threadpoolexecutor.workqueue;

import com.java.xknowledge.se.thread.threadpool.MyRunnable;
import com.java.xknowledge.se.thread.threadpool.MyThreadFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * SynchronizedQueue测试（同步队列）：特殊的BlockingQueue，对其的操作必须是放和取交替完成，立即执行！！
 */
class SynchronizedQueueTest {
    public static void main0(String[] args) {
        //corePoolSize = 5,maximumPoolSize = 8
        //currentTime = 02:21:49，第 0 Runnable，使用线程pool-1-thread-1开始执行....//立即创建5个核心线程执行任务
        //currentTime = 02:21:49，第 1 Runnable，使用线程pool-1-thread-2开始执行....
        //currentTime = 02:21:49，第 2 Runnable，使用线程pool-1-thread-3开始执行....
        //currentTime = 02:21:49，第 3 Runnable，使用线程pool-1-thread-4开始执行....
        //currentTime = 02:21:49，第 4 Runnable，使用线程pool-1-thread-5开始执行....
        //currentTime = 02:21:49，第 5 Runnable，使用线程pool-1-thread-6开始执行....//进入同步队列，立即创建最大线程执行任务
        //currentTime = 02:21:49，第 6 Runnable，使用线程pool-1-thread-7开始执行....
        //currentTime = 02:21:49，第 7 Runnable，使用线程pool-1-thread-8开始执行....
        //Exception in thread "main" java.util.concurrent.RejectedExecutionException: Task com.java.xknowledge.se.thread.threadpool.MyRunnable@63947c6b rejected from java.util.concurrent.ThreadPoolExecutor@2b193f2d[Running, pool size = 8, active threads = 8, queued tasks = 0, completed tasks = 0]
        //	at java.util.concurrent.ThreadPoolExecutor$AbortPolicy.rejectedExecution(ThreadPoolExecutor.java:2063)
        //	at java.util.concurrent.ThreadPoolExecutor.reject(ThreadPoolExecutor.java:830)
        //	at java.util.concurrent.ThreadPoolExecutor.execute(ThreadPoolExecutor.java:1379)
        //	at com.java.xknowledge.se.thread.threadpool.threadpoolexecutor.workqueue.SynchronizedQueueTest.main(SynchronizedQueueTest.java:23)//最大线程满了则抛弃
        //currentTime = 02:21:50，第 2 Runnable，使用线程pool-1-thread-3执行完毕----
        //currentTime = 02:21:50，第 0 Runnable，使用线程pool-1-thread-1执行完毕----
        //currentTime = 02:21:50，第 3 Runnable，使用线程pool-1-thread-4执行完毕----
        //currentTime = 02:21:50，第 4 Runnable，使用线程pool-1-thread-5执行完毕----
        //currentTime = 02:21:50，第 1 Runnable，使用线程pool-1-thread-2执行完毕----
        //currentTime = 02:21:50，第 5 Runnable，使用线程pool-1-thread-6执行完毕----
        //currentTime = 02:21:50，第 6 Runnable，使用线程pool-1-thread-7执行完毕----
        //currentTime = 02:21:50，第 7 Runnable，使用线程pool-1-thread-8执行完毕----
        int corePoolSize = Runtime.getRuntime().availableProcessors() + 1;
        int maximumPoolSize = Runtime.getRuntime().availableProcessors() * 2;
        System.out.println("corePoolSize = " + corePoolSize + ",maximumPoolSize = " + maximumPoolSize);
        //同步阻塞队列：入队列立即出队列，没有缓存作用，立即执行
        BlockingQueue<Runnable> synchronousQueue = new SynchronousQueue<>();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, 10L, TimeUnit.SECONDS, synchronousQueue, new MyThreadFactory());
        for (int i = 0; i < 20; i++) {
            threadPoolExecutor.execute(new MyRunnable(i, 1000));
        }
    }

    public static void main(String[] args) {
        //最大线程数能满足20个任务，先创建5个核心线程执行任务，加入同步队列，立即出队列创建15个最大线程执行任务，没有阻塞
        //corePoolSize = 5,maximumPoolSize = 20
        //currentTime = 02:24:18，第 0 Runnable，使用线程pool-1-thread-1开始执行....
        //currentTime = 02:24:18，第 1 Runnable，使用线程pool-1-thread-2开始执行....
        //currentTime = 02:24:18，第 2 Runnable，使用线程pool-1-thread-3开始执行....
        //currentTime = 02:24:18，第 3 Runnable，使用线程pool-1-thread-4开始执行....
        //currentTime = 02:24:18，第 4 Runnable，使用线程pool-1-thread-5开始执行....
        //currentTime = 02:24:18，第 5 Runnable，使用线程pool-1-thread-6开始执行....
        //currentTime = 02:24:18，第 6 Runnable，使用线程pool-1-thread-7开始执行....
        //currentTime = 02:24:18，第 7 Runnable，使用线程pool-1-thread-8开始执行....
        //currentTime = 02:24:18，第 8 Runnable，使用线程pool-1-thread-9开始执行....
        //currentTime = 02:24:18，第 9 Runnable，使用线程pool-1-thread-10开始执行....
        //currentTime = 02:24:18，第 10 Runnable，使用线程pool-1-thread-11开始执行....
        //currentTime = 02:24:18，第 11 Runnable，使用线程pool-1-thread-12开始执行....
        //currentTime = 02:24:18，第 12 Runnable，使用线程pool-1-thread-13开始执行....
        //currentTime = 02:24:18，第 13 Runnable，使用线程pool-1-thread-14开始执行....
        //currentTime = 02:24:18，第 14 Runnable，使用线程pool-1-thread-15开始执行....
        //currentTime = 02:24:18，第 15 Runnable，使用线程pool-1-thread-16开始执行....
        //currentTime = 02:24:18，第 16 Runnable，使用线程pool-1-thread-17开始执行....
        //currentTime = 02:24:18，第 17 Runnable，使用线程pool-1-thread-18开始执行....
        //currentTime = 02:24:18，第 18 Runnable，使用线程pool-1-thread-19开始执行....
        //currentTime = 02:24:18，第 19 Runnable，使用线程pool-1-thread-20开始执行....
        //currentTime = 02:24:19，第 2 Runnable，使用线程pool-1-thread-3执行完毕----
        //currentTime = 02:24:19，第 4 Runnable，使用线程pool-1-thread-5执行完毕----
        //currentTime = 02:24:19，第 6 Runnable，使用线程pool-1-thread-7执行完毕----
        //currentTime = 02:24:19，第 7 Runnable，使用线程pool-1-thread-8执行完毕----
        //currentTime = 02:24:19，第 10 Runnable，使用线程pool-1-thread-11执行完毕----
        //currentTime = 02:24:19，第 3 Runnable，使用线程pool-1-thread-4执行完毕----
        //currentTime = 02:24:19，第 1 Runnable，使用线程pool-1-thread-2执行完毕----
        //currentTime = 02:24:19，第 0 Runnable，使用线程pool-1-thread-1执行完毕----
        //currentTime = 02:24:19，第 9 Runnable，使用线程pool-1-thread-10执行完毕----
        //currentTime = 02:24:19，第 8 Runnable，使用线程pool-1-thread-9执行完毕----
        //currentTime = 02:24:19，第 5 Runnable，使用线程pool-1-thread-6执行完毕----
        //currentTime = 02:24:19，第 13 Runnable，使用线程pool-1-thread-14执行完毕----
        //currentTime = 02:24:19，第 11 Runnable，使用线程pool-1-thread-12执行完毕----
        //currentTime = 02:24:19，第 14 Runnable，使用线程pool-1-thread-15执行完毕----
        //currentTime = 02:24:19，第 15 Runnable，使用线程pool-1-thread-16执行完毕----
        //currentTime = 02:24:19，第 12 Runnable，使用线程pool-1-thread-13执行完毕----
        //currentTime = 02:24:19，第 16 Runnable，使用线程pool-1-thread-17执行完毕----
        //currentTime = 02:24:19，第 19 Runnable，使用线程pool-1-thread-20执行完毕----
        //currentTime = 02:24:19，第 18 Runnable，使用线程pool-1-thread-19执行完毕----
        //currentTime = 02:24:19，第 17 Runnable，使用线程pool-1-thread-18执行完毕----
        int corePoolSize =5;
        int maximumPoolSize = 20;
        System.out.println("corePoolSize = " + corePoolSize + ",maximumPoolSize = " + maximumPoolSize);
        //同步阻塞队列：入队列立即出队列，没有缓存作用，立即执行
        BlockingQueue<Runnable> synchronousQueue = new SynchronousQueue<>();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, 10L, TimeUnit.SECONDS, synchronousQueue, new MyThreadFactory());
        for (int i = 0; i < 20; i++) {
            threadPoolExecutor.execute(new MyRunnable(i, 1000));
        }
    }
}
