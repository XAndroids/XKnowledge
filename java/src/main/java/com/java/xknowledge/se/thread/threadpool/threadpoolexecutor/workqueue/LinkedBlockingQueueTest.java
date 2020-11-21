package com.java.xknowledge.se.thread.threadpool.threadpoolexecutor.workqueue;

import com.java.xknowledge.se.thread.threadpool.MyRunnable;
import com.java.xknowledge.se.thread.threadpool.MyThreadFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * LinkedBlockingQueue实践：
 *
 */
class LinkedBlockingQueueTest {

    //new LinkedBlockingQueue<>()，无界队列
    public static void main1(String[] args) {
        //LinkedBlockingQueue默认Int.MAX_VALUE，无界，任务当前没有空闲线程执行，都加入阻塞队列等待执行，无拒绝
        //corePoolSize = 5,maximumPoolSize = 8
        //currentTime = 12:35:00，第 0 Runnable，使用线程pool-1-thread-1开始执行....
        //currentTime = 12:35:00，第 1 Runnable，使用线程pool-1-thread-2开始执行....
        //currentTime = 12:35:00，第 2 Runnable，使用线程pool-1-thread-3开始执行....
        //currentTime = 12:35:00，第 3 Runnable，使用线程pool-1-thread-4开始执行....
        //currentTime = 12:35:00，第 4 Runnable，使用线程pool-1-thread-5开始执行....
        //currentTime = 12:35:01，第 1 Runnable，使用线程pool-1-thread-2执行完毕----
        //currentTime = 12:35:01，第 4 Runnable，使用线程pool-1-thread-5执行完毕----
        //currentTime = 12:35:01，第 3 Runnable，使用线程pool-1-thread-4执行完毕----
        //currentTime = 12:35:01，第 5 Runnable，使用线程pool-1-thread-2开始执行....
        //currentTime = 12:35:01，第 6 Runnable，使用线程pool-1-thread-5开始执行....
        //currentTime = 12:35:01，第 2 Runnable，使用线程pool-1-thread-3执行完毕----
        //currentTime = 12:35:01，第 7 Runnable，使用线程pool-1-thread-4开始执行....
        //currentTime = 12:35:01，第 0 Runnable，使用线程pool-1-thread-1执行完毕----
        //currentTime = 12:35:01，第 8 Runnable，使用线程pool-1-thread-3开始执行....
        //currentTime = 12:35:01，第 9 Runnable，使用线程pool-1-thread-1开始执行....
        //currentTime = 12:35:02，第 6 Runnable，使用线程pool-1-thread-5执行完毕----
        //currentTime = 12:35:02，第 5 Runnable，使用线程pool-1-thread-2执行完毕----
        //currentTime = 12:35:02，第 10 Runnable，使用线程pool-1-thread-5开始执行....
        //currentTime = 12:35:02，第 11 Runnable，使用线程pool-1-thread-2开始执行....
        //currentTime = 12:35:02，第 8 Runnable，使用线程pool-1-thread-3执行完毕----
        //currentTime = 12:35:02，第 7 Runnable，使用线程pool-1-thread-4执行完毕----
        //currentTime = 12:35:02，第 9 Runnable，使用线程pool-1-thread-1执行完毕----
        //currentTime = 12:35:02，第 13 Runnable，使用线程pool-1-thread-4开始执行....
        //currentTime = 12:35:02，第 14 Runnable，使用线程pool-1-thread-1开始执行....
        //currentTime = 12:35:02，第 12 Runnable，使用线程pool-1-thread-3开始执行....
        //currentTime = 12:35:03，第 10 Runnable，使用线程pool-1-thread-5执行完毕----
        //currentTime = 12:35:03，第 11 Runnable，使用线程pool-1-thread-2执行完毕----
        //currentTime = 12:35:03，第 15 Runnable，使用线程pool-1-thread-5开始执行....
        //currentTime = 12:35:03，第 16 Runnable，使用线程pool-1-thread-2开始执行....
        //currentTime = 12:35:03，第 13 Runnable，使用线程pool-1-thread-4执行完毕----
        //currentTime = 12:35:03，第 14 Runnable，使用线程pool-1-thread-1执行完毕----
        //currentTime = 12:35:03，第 17 Runnable，使用线程pool-1-thread-4开始执行....
        //currentTime = 12:35:03，第 18 Runnable，使用线程pool-1-thread-1开始执行....
        //currentTime = 12:35:03，第 12 Runnable，使用线程pool-1-thread-3执行完毕----
        //currentTime = 12:35:03，第 19 Runnable，使用线程pool-1-thread-3开始执行....
        //currentTime = 12:35:04，第 19 Runnable，使用线程pool-1-thread-3执行完毕----
        //currentTime = 12:35:04，第 15 Runnable，使用线程pool-1-thread-5执行完毕----
        //currentTime = 12:35:04，第 17 Runnable，使用线程pool-1-thread-4执行完毕----
        //currentTime = 12:35:04，第 18 Runnable，使用线程pool-1-thread-1执行完毕----
        //currentTime = 12:35:04，第 16 Runnable，使用线程pool-1-thread-2执行完毕----
        int corePoolSize = Runtime.getRuntime().availableProcessors() + 1;
        int maximumPoolSize = Runtime.getRuntime().availableProcessors() * 2;
        System.out.println("corePoolSize = " + corePoolSize + ",maximumPoolSize = " + maximumPoolSize);
        //无界阻塞队列：当前无法执行的任务，在队列中等待，不会Rejected处理
        BlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<>();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, 10L, TimeUnit.SECONDS, blockingQueue, new MyThreadFactory());
        for (int i = 0; i < 20; i++) {
            threadPoolExecutor.execute(new MyRunnable(i, 1000));
        }
    }

    //new LinkedBlockingQueue<>(10);有界队列
    public static void main(String[] args) {
        //corePoolSize = 5,maximumPoolSize = 8
        //currentTime = 12:53:21，第 0 Runnable，使用线程pool-1-thread-1开始执行.....//创建5个核心线程执行任务
        //currentTime = 12:53:21，第 1 Runnable，使用线程pool-1-thread-2开始执行....
        //currentTime = 12:53:21，第 2 Runnable，使用线程pool-1-thread-3开始执行....
        //currentTime = 12:53:21，第 3 Runnable，使用线程pool-1-thread-4开始执行....
        //currentTime = 12:53:21，第 4 Runnable，使用线程pool-1-thread-5开始执行...//5~14个任务进入容量为10的队列等待
        //currentTime = 12:53:21，第 15 Runnable，使用线程pool-1-thread-6开始执行....//队列满了，创建3个最大线程执行任务15~17
        //currentTime = 12:53:21，第 16 Runnable，使用线程pool-1-thread-7开始执行....
        //currentTime = 12:53:21，第 17 Runnable，使用线程pool-1-thread-8开始执行....
        //Exception in thread "main" java.util.concurrent.RejectedExecutionException: Task com.java.xknowledge.se.thread.threadpool.MyRunnable@66d3c617 rejected from java.util.concurrent.ThreadPoolExecutor@63947c6b[Running, pool size = 8, active threads = 8, queued tasks = 10, completed tasks = 0]
        //	at java.util.concurrent.ThreadPoolExecutor$AbortPolicy.rejectedExecution(ThreadPoolExecutor.java:2063)
        //	at java.util.concurrent.ThreadPoolExecutor.reject(ThreadPoolExecutor.java:830)
        //	at java.util.concurrent.ThreadPoolExecutor.execute(ThreadPoolExecutor.java:1379)
        //	at com.java.xknowledge.se.thread.threadpool.threadpoolexecutor.workqueue.LinkedBlockingQueueTest.main(LinkedBlockingQueueTest.java:79)//18~20个任务无法执行，拒绝抛出异常
        //currentTime = 12:53:26，第 0 Runnable，使用线程pool-1-thread-1执行完毕----
        //currentTime = 12:53:26，第 1 Runnable，使用线程pool-1-thread-2执行完毕----
        //currentTime = 12:53:26，第 5 Runnable，使用线程pool-1-thread-2开始执行....//0、1线程执行完毕，从队列中取出5、6任务执行
        //currentTime = 12:53:26，第 6 Runnable，使用线程pool-1-thread-1开始执行....
        //currentTime = 12:53:26，第 4 Runnable，使用线程pool-1-thread-5执行完毕----
        //currentTime = 12:53:26，第 15 Runnable，使用线程pool-1-thread-6执行完毕----
        //currentTime = 12:53:26，第 16 Runnable，使用线程pool-1-thread-7执行完毕----
        //currentTime = 12:53:26，第 8 Runnable，使用线程pool-1-thread-6开始执行....
        //currentTime = 12:53:26，第 9 Runnable，使用线程pool-1-thread-7开始执行....
        //currentTime = 12:53:26，第 2 Runnable，使用线程pool-1-thread-3执行完毕----
        //currentTime = 12:53:26，第 3 Runnable，使用线程pool-1-thread-4执行完毕----
        //currentTime = 12:53:26，第 10 Runnable，使用线程pool-1-thread-3开始执行....
        //currentTime = 12:53:26，第 17 Runnable，使用线程pool-1-thread-8执行完毕----
        //currentTime = 12:53:26，第 7 Runnable，使用线程pool-1-thread-5开始执行....
        //currentTime = 12:53:26，第 11 Runnable，使用线程pool-1-thread-4开始执行....
        //currentTime = 12:53:26，第 12 Runnable，使用线程pool-1-thread-8开始执行....
        //currentTime = 12:53:31，第 5 Runnable，使用线程pool-1-thread-2执行完毕----
        //currentTime = 12:53:31，第 6 Runnable，使用线程pool-1-thread-1执行完毕----
        //currentTime = 12:53:31，第 13 Runnable，使用线程pool-1-thread-2开始执行....
        //currentTime = 12:53:31，第 14 Runnable，使用线程pool-1-thread-1开始执行....
        //currentTime = 12:53:31，第 9 Runnable，使用线程pool-1-thread-7执行完毕----
        //currentTime = 12:53:31，第 7 Runnable，使用线程pool-1-thread-5执行完毕----
        //currentTime = 12:53:31，第 12 Runnable，使用线程pool-1-thread-8执行完毕----
        //currentTime = 12:53:31，第 8 Runnable，使用线程pool-1-thread-6执行完毕----
        //currentTime = 12:53:31，第 10 Runnable，使用线程pool-1-thread-3执行完毕----
        //currentTime = 12:53:31，第 11 Runnable，使用线程pool-1-thread-4执行完毕----
        //currentTime = 12:53:36，第 13 Runnable，使用线程pool-1-thread-2执行完毕----
        //currentTime = 12:53:36，第 14 Runnable，使用线程pool-1-thread-1执行完毕----
        int corePoolSize = Runtime.getRuntime().availableProcessors() + 1;
        int maximumPoolSize = Runtime.getRuntime().availableProcessors() * 2;
        System.out.println("corePoolSize = " + corePoolSize + ",maximumPoolSize = " + maximumPoolSize);
        //有界阻塞队列，容量为10，核心线程5个，最大线程3个，阻塞对10个，18、19两个任务会被拒绝丢弃
        BlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<>(10);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, 10L, TimeUnit.SECONDS, blockingQueue, new MyThreadFactory());
        for (int i = 0; i < 20; i++) {
            threadPoolExecutor.execute(new MyRunnable(i, 5000));
        }
    }
}
