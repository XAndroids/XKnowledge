package com.java.xknowledge.se.thread.threadpool.threadpoolexecutor.workqueue;

import com.java.xknowledge.se.thread.threadpool.MyThreadFactory;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * PriorityBlockingQueue优先队列测试实践
 * 1.和ArrayBlockingQueue一样基于数组实现，但PriorityBlockingQueue初始化时指定长度，默认11
 * 2.该队列可以说是真正的无界队列，它在队列满的时候进行扩容，而LinkedBlockingQueue其实是有界的，只是节点为Integer.MAX_VALUE；
 * 3.该队列属于权重队列，它可以进行排序，基于数组的堆结构从小到大或者从大大小；
 * 4.出队列根据权重来出队列，和先进先出不同；
 * 参考：优先级队列原理：https://blog.csdn.net/weixin_41622183/article/details/89303031
 * 优先级队列使用：http://liuxiang.github.io/2017/06/26/%E4%BE%9D%E6%8D%AE%E4%BB%BB%E5%8A%A1%E4%BC%98%E5%85%88%E7%BA%A7,%E5%AE%9E%E7%8E%B0%E5%8F%AF%E6%8F%92%E9%98%9F%E7%9A%84%E7%BA%BF%E7%A8%8B%E6%B1%A0-%E7%AE%80%E8%A3%85/
 */
class PriorityBlockingQueueTest {
    public static void main(String[] args) {
        //corePoolSize = 5,maximumPoolSize = 20
        //currentTime = 03:15:09，第 0 PriorityRunnable，使用线程pool-1-thread-1开始执行....//创建5个核心线程执行0~4个任务
        //currentTime = 03:15:09，第 1 PriorityRunnable，使用线程pool-1-thread-2开始执行....
        //currentTime = 03:15:09，第 2 PriorityRunnable，使用线程pool-1-thread-3开始执行....
        //currentTime = 03:15:09，第 3 PriorityRunnable，使用线程pool-1-thread-4开始执行....
        //currentTime = 03:15:09，第 4 PriorityRunnable，使用线程pool-1-thread-5开始执行....
        //currentTime = 03:15:10，第 0 PriorityRunnable，使用线程pool-1-thread-1执行完毕----//第0个任务执行完毕，按照优先级从第19个任务开始先执行
        //currentTime = 03:15:10，第 19 PriorityRunnable，使用线程pool-1-thread-1开始执行....
        //currentTime = 03:15:10，第 2 PriorityRunnable，使用线程pool-1-thread-3执行完毕----
        //currentTime = 03:15:10，第 1 PriorityRunnable，使用线程pool-1-thread-2执行完毕----
        //currentTime = 03:15:10，第 18 PriorityRunnable，使用线程pool-1-thread-3开始执行....
        //currentTime = 03:15:10，第 17 PriorityRunnable，使用线程pool-1-thread-2开始执行....
        //currentTime = 03:15:10，第 3 PriorityRunnable，使用线程pool-1-thread-4执行完毕----
        //currentTime = 03:15:10，第 16 PriorityRunnable，使用线程pool-1-thread-4开始执行....
        //currentTime = 03:15:10，第 4 PriorityRunnable，使用线程pool-1-thread-5执行完毕----
        //currentTime = 03:15:10，第 15 PriorityRunnable，使用线程pool-1-thread-5开始执行....
        //currentTime = 03:15:11，第 19 PriorityRunnable，使用线程pool-1-thread-1执行完毕----
        //currentTime = 03:15:11，第 14 PriorityRunnable，使用线程pool-1-thread-1开始执行....
        //currentTime = 03:15:11，第 17 PriorityRunnable，使用线程pool-1-thread-2执行完毕----
        //currentTime = 03:15:11，第 18 PriorityRunnable，使用线程pool-1-thread-3执行完毕----
        //currentTime = 03:15:11，第 13 PriorityRunnable，使用线程pool-1-thread-2开始执行....
        //currentTime = 03:15:11，第 12 PriorityRunnable，使用线程pool-1-thread-3开始执行....
        //currentTime = 03:15:11，第 16 PriorityRunnable，使用线程pool-1-thread-4执行完毕----
        //currentTime = 03:15:11，第 11 PriorityRunnable，使用线程pool-1-thread-4开始执行....
        //currentTime = 03:15:11，第 15 PriorityRunnable，使用线程pool-1-thread-5执行完毕----
        //currentTime = 03:15:11，第 10 PriorityRunnable，使用线程pool-1-thread-5开始执行....
        //currentTime = 03:15:12，第 14 PriorityRunnable，使用线程pool-1-thread-1执行完毕----
        //currentTime = 03:15:12，第 9 PriorityRunnable，使用线程pool-1-thread-1开始执行....
        //currentTime = 03:15:12，第 13 PriorityRunnable，使用线程pool-1-thread-2执行完毕----
        //currentTime = 03:15:12，第 8 PriorityRunnable，使用线程pool-1-thread-2开始执行....
        //currentTime = 03:15:12，第 12 PriorityRunnable，使用线程pool-1-thread-3执行完毕----
        //currentTime = 03:15:12，第 7 PriorityRunnable，使用线程pool-1-thread-3开始执行....
        //currentTime = 03:15:12，第 11 PriorityRunnable，使用线程pool-1-thread-4执行完毕----
        //currentTime = 03:15:12，第 6 PriorityRunnable，使用线程pool-1-thread-4开始执行....
        //currentTime = 03:15:12，第 10 PriorityRunnable，使用线程pool-1-thread-5执行完毕----
        //currentTime = 03:15:12，第 5 PriorityRunnable，使用线程pool-1-thread-5开始执行....
        //currentTime = 03:15:13，第 9 PriorityRunnable，使用线程pool-1-thread-1执行完毕----
        //currentTime = 03:15:13，第 8 PriorityRunnable，使用线程pool-1-thread-2执行完毕----
        //currentTime = 03:15:13，第 7 PriorityRunnable，使用线程pool-1-thread-3执行完毕----
        //currentTime = 03:15:13，第 6 PriorityRunnable，使用线程pool-1-thread-4执行完毕----
        //currentTime = 03:15:13，第 5 PriorityRunnable，使用线程pool-1-thread-5执行完毕----
        int corePoolSize = 5;
        int maximumPoolSize = 20;
        System.out.println("corePoolSize = " + corePoolSize + ",maximumPoolSize = " + maximumPoolSize);
        //同步阻塞队列：入队列立即出队列，没有缓存作用，立即执行
        PriorityBlockingQueue priorityBlockingQueue = new PriorityBlockingQueue();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, 10L, TimeUnit.SECONDS, priorityBlockingQueue, new MyThreadFactory());
        for (int i = 0; i < 20; i++) {
            threadPoolExecutor.execute(new PriorityRunnable(i, 1000));
        }
    }
}
