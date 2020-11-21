package com.java.xknowledge.se.thread.threadpool.threadpoolexecutor.workqueue;

import com.java.xknowledge.se.thread.threadpool.MyRunnable;
import com.java.xknowledge.se.thread.threadpool.MyThreadFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ArrayBlockingQueueTest，有界等待队列测试：
 * 规定大小的BlockingQueue，其构造必须指定大小。其所含的对象是FIFO顺序排序的。
 * LinkedBlockingQueue和ArrayBlockingQueue的区别：
 * 1.ArrayBlockingQueue是有界的，LinkedBlockingQueue默认是无界的；
 * 2.ArrayBlockingQueue内部使用1个锁控制队列项的插入、取出、而LinkedBlockingQueue使用2个锁，一个pubLock，一个takeLock，本质是ReentrantLock
 * 因为LinkedBlockingQueue使用2个锁，一定程度上能更好的发挥并发场景操作，不是吞吐量
 * 3.ArrayBlockingQueue内部使用数组空间，预先分配好，操作不复杂；LinkedBlockingQueue中，使用链表，插入需要转为Node<e>对象，
 * 相当于多一步操作，但官方描述它具有更好的吞吐量，可能跟锁有关；
 * 参考：https://blog.csdn.net/Androidlushangderen/article/details/80219264
 */
class ArrayBlockingQueueTest {
    public static void main0(String[] args) {
        //corePoolSize = 5,maximumPoolSize = 8
        //currentTime = 02:14:49，第 0 Runnable，使用线程pool-1-thread-1开始执行....//创建5个核心线程，执行0~4个任务
        //currentTime = 02:14:49，第 1 Runnable，使用线程pool-1-thread-2开始执行....
        //currentTime = 02:14:49，第 2 Runnable，使用线程pool-1-thread-3开始执行....
        //currentTime = 02:14:49，第 3 Runnable，使用线程pool-1-thread-4开始执行....
        //currentTime = 02:14:49，第 4 Runnable，使用线程pool-1-thread-5开始执行....
        //currentTime = 02:14:49，第 15 Runnable，使用线程pool-1-thread-6开始执行....//5~14进入等待队列直到满，创建3个最大线程执15~17行
        //currentTime = 02:14:49，第 16 Runnable，使用线程pool-1-thread-7开始执行....
        //currentTime = 02:14:49，第 17 Runnable，使用线程pool-1-thread-8开始执行....
        //Exception in thread "main" java.util.concurrent.RejectedExecutionException: Task com.java.xknowledge.se.thread.threadpool.MyRunnable@66d3c617 rejected from java.util.concurrent.ThreadPoolExecutor@63947c6b[Running, pool size = 8, active threads = 8, queued tasks = 10, completed tasks = 0]
        //	at java.util.concurrent.ThreadPoolExecutor$AbortPolicy.rejectedExecution(ThreadPoolExecutor.java:2063)
        //	at java.util.concurrent.ThreadPoolExecutor.reject(ThreadPoolExecutor.java:830)
        //	at java.util.concurrent.ThreadPoolExecutor.execute(ThreadPoolExecutor.java:1379)
        //	at com.java.xknowledge.se.thread.threadpool.threadpoolexecutor.workqueue.ArrayBlockingQueueTest.main(ArrayBlockingQueueTest.java:31)//18~19被拒绝
        //currentTime = 02:14:50，第 0 Runnable，使用线程pool-1-thread-1执行完毕----
        //currentTime = 02:14:50，第 1 Runnable，使用线程pool-1-thread-2执行完毕----
        //currentTime = 02:14:50，第 5 Runnable，使用线程pool-1-thread-1开始执行....//0、1执行完毕后，等待队列中先先进入的6、7开始执行
        //currentTime = 02:14:50，第 6 Runnable，使用线程pool-1-thread-2开始执行....
        //currentTime = 02:14:50，第 2 Runnable，使用线程pool-1-thread-3执行完毕----
        //currentTime = 02:14:50，第 7 Runnable，使用线程pool-1-thread-3开始执行....
        //currentTime = 02:14:50，第 15 Runnable，使用线程pool-1-thread-6执行完毕----
        //currentTime = 02:14:50，第 4 Runnable，使用线程pool-1-thread-5执行完毕----
        //currentTime = 02:14:50，第 3 Runnable，使用线程pool-1-thread-4执行完毕----
        //currentTime = 02:14:50，第 8 Runnable，使用线程pool-1-thread-6开始执行....
        //currentTime = 02:14:50，第 10 Runnable，使用线程pool-1-thread-4开始执行....
        //currentTime = 02:14:50，第 9 Runnable，使用线程pool-1-thread-5开始执行....
        //currentTime = 02:14:50，第 17 Runnable，使用线程pool-1-thread-8执行完毕----
        //currentTime = 02:14:50，第 16 Runnable，使用线程pool-1-thread-7执行完毕----
        //currentTime = 02:14:50，第 11 Runnable，使用线程pool-1-thread-8开始执行....
        //currentTime = 02:14:50，第 12 Runnable，使用线程pool-1-thread-7开始执行....
        //currentTime = 02:14:51，第 6 Runnable，使用线程pool-1-thread-2执行完毕----
        //currentTime = 02:14:51，第 5 Runnable，使用线程pool-1-thread-1执行完毕----
        //currentTime = 02:14:51，第 7 Runnable，使用线程pool-1-thread-3执行完毕----
        //currentTime = 02:14:51，第 14 Runnable，使用线程pool-1-thread-1开始执行....
        //currentTime = 02:14:51，第 13 Runnable，使用线程pool-1-thread-2开始执行....
        //currentTime = 02:14:51，第 9 Runnable，使用线程pool-1-thread-5执行完毕----
        //currentTime = 02:14:51，第 8 Runnable，使用线程pool-1-thread-6执行完毕----
        //currentTime = 02:14:51，第 10 Runnable，使用线程pool-1-thread-4执行完毕----
        int corePoolSize = Runtime.getRuntime().availableProcessors() + 1;
        int maximumPoolSize = Runtime.getRuntime().availableProcessors() * 2;
        System.out.println("corePoolSize = " + corePoolSize + ",maximumPoolSize = " + maximumPoolSize);
        //有界阻塞队列：容量10，核心线程+阻塞队列+（最大-核心）线程<要执行的任务数量，会出现拒绝执行
        BlockingQueue<Runnable> arrayBlockingQueue = new ArrayBlockingQueue<>(10);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, 10L, TimeUnit.SECONDS, arrayBlockingQueue, new MyThreadFactory());
        for (int i = 0; i < 20; i++) {
            threadPoolExecutor.execute(new MyRunnable(i, 1000));
        }
    }

    //等待队列100个，足够容纳20个任务，故不会创建最大线程，不会拒绝。依次等待核心线程空闲执行
    //corePoolSize = 5,maximumPoolSize = 8
    //currentTime = 02:17:18，第 1 Runnable，使用线程pool-1-thread-2开始执行....
    //currentTime = 02:17:18，第 0 Runnable，使用线程pool-1-thread-1开始执行....
    //currentTime = 02:17:18，第 2 Runnable，使用线程pool-1-thread-3开始执行....
    //currentTime = 02:17:18，第 3 Runnable，使用线程pool-1-thread-4开始执行....
    //currentTime = 02:17:18，第 4 Runnable，使用线程pool-1-thread-5开始执行....
    //currentTime = 02:17:19，第 0 Runnable，使用线程pool-1-thread-1执行完毕----
    //currentTime = 02:17:19，第 1 Runnable，使用线程pool-1-thread-2执行完毕----
    //currentTime = 02:17:19，第 2 Runnable，使用线程pool-1-thread-3执行完毕----
    //currentTime = 02:17:19，第 5 Runnable，使用线程pool-1-thread-1开始执行....
    //currentTime = 02:17:19，第 7 Runnable，使用线程pool-1-thread-2开始执行....
    //currentTime = 02:17:19，第 6 Runnable，使用线程pool-1-thread-3开始执行....
    //currentTime = 02:17:19，第 3 Runnable，使用线程pool-1-thread-4执行完毕----
    //currentTime = 02:17:19，第 4 Runnable，使用线程pool-1-thread-5执行完毕----
    //currentTime = 02:17:19，第 8 Runnable，使用线程pool-1-thread-4开始执行....
    //currentTime = 02:17:19，第 9 Runnable，使用线程pool-1-thread-5开始执行....
    //currentTime = 02:17:20，第 6 Runnable，使用线程pool-1-thread-3执行完毕----
    //currentTime = 02:17:20，第 5 Runnable，使用线程pool-1-thread-1执行完毕----
    //currentTime = 02:17:20，第 7 Runnable，使用线程pool-1-thread-2执行完毕----
    //currentTime = 02:17:20，第 11 Runnable，使用线程pool-1-thread-1开始执行....
    //currentTime = 02:17:20，第 12 Runnable，使用线程pool-1-thread-2开始执行....
    //currentTime = 02:17:20，第 10 Runnable，使用线程pool-1-thread-3开始执行....
    //currentTime = 02:17:20，第 9 Runnable，使用线程pool-1-thread-5执行完毕----
    //currentTime = 02:17:20，第 8 Runnable，使用线程pool-1-thread-4执行完毕----
    //currentTime = 02:17:20，第 13 Runnable，使用线程pool-1-thread-5开始执行....
    //currentTime = 02:17:20，第 14 Runnable，使用线程pool-1-thread-4开始执行....
    //currentTime = 02:17:21，第 11 Runnable，使用线程pool-1-thread-1执行完毕----
    //currentTime = 02:17:21，第 12 Runnable，使用线程pool-1-thread-2执行完毕----
    //currentTime = 02:17:21，第 15 Runnable，使用线程pool-1-thread-1开始执行....
    //currentTime = 02:17:21，第 16 Runnable，使用线程pool-1-thread-2开始执行....
    //currentTime = 02:17:21，第 10 Runnable，使用线程pool-1-thread-3执行完毕----
    //currentTime = 02:17:21，第 17 Runnable，使用线程pool-1-thread-3开始执行....
    //currentTime = 02:17:21，第 13 Runnable，使用线程pool-1-thread-5执行完毕----
    //currentTime = 02:17:21，第 14 Runnable，使用线程pool-1-thread-4执行完毕----
    //currentTime = 02:17:21，第 18 Runnable，使用线程pool-1-thread-5开始执行....
    //currentTime = 02:17:21，第 19 Runnable，使用线程pool-1-thread-4开始执行....
    //currentTime = 02:17:22，第 15 Runnable，使用线程pool-1-thread-1执行完毕----
    //currentTime = 02:17:22，第 17 Runnable，使用线程pool-1-thread-3执行完毕----
    //currentTime = 02:17:22，第 16 Runnable，使用线程pool-1-thread-2执行完毕----
    //currentTime = 02:17:22，第 18 Runnable，使用线程pool-1-thread-5执行完毕----
    //currentTime = 02:17:22，第 19 Runnable，使用线程pool-1-thread-4执行完毕----
    public static void main(String[] args) {
        int corePoolSize = Runtime.getRuntime().availableProcessors() + 1;
        int maximumPoolSize = Runtime.getRuntime().availableProcessors() * 2;
        System.out.println("corePoolSize = " + corePoolSize + ",maximumPoolSize = " + maximumPoolSize);
        //有界阻塞队列：容量10，核心线程+阻塞队列+（最大-核心）线程>要执行的任务数量，不会出现
        BlockingQueue<Runnable> arrayBlockingQueue = new ArrayBlockingQueue<>(100);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, 10L, TimeUnit.SECONDS, arrayBlockingQueue, new MyThreadFactory());
        for (int i = 0; i < 20; i++) {
            threadPoolExecutor.execute(new MyRunnable(i, 1000));
        }
    }
}


