package com.java.xknowledge.se.thread.threadpool.threadpoolexecutor.rejected;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal默认拒绝策略：AbortPolicy，直接抛出异常
 * 运行：
 * 添加第0个任务
 * 添加第1个任务
 * 列表:线程1
 * 添加第2个任务
 * 列表:线程1
 * 列表:线程2
 * 添加第3个任务
 * 列表:线程1
 * 列表:线程2
 * 添加第4个任务
 * Exception in thread "main" java.util.concurrent.RejectedExecutionException: Task com.java.xknowledge.se.thread.threadpool.threadpoolexecutor.rejected.MyThread@29453f44 rejected from java.util.concurrent.ThreadPoolExecutor@5cad8086[Running, pool size = 2, active threads = 2, queued tasks = 2, completed tasks = 0]
 * 	at java.util.concurrent.ThreadPoolExecutor$AbortPolicy.rejectedExecution(ThreadPoolExecutor.java:2063)
 * 	at java.util.concurrent.ThreadPoolExecutor.reject(ThreadPoolExecutor.java:830)
 * 	at java.util.concurrent.ThreadPoolExecutor.execute(ThreadPoolExecutor.java:1379)
 * 	at com.java.xknowledge.se.thread.threadpool.threadpoolexecutor.rejected.AbortPolicyTest.main(AbortPolicyTest.java:19)
 * 线程:pool-1-thread-1 执行:线程0run
 * 线程:pool-1-thread-2 执行:线程3run
 * 线程:pool-1-thread-1 执行:线程2run
 * 线程:pool-1-thread-2 执行:线程1run
 */
class AbortPolicyTest {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 2,
                0, TimeUnit.MICROSECONDS, new LinkedBlockingDeque<>(2),
                new ThreadPoolExecutor.AbortPolicy());

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
