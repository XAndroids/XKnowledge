package com.java.xknowledge.se.thread.threadpool.executors;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Executors.newScheduledThreadPool周期线程池测试类：
 * Executors.newScheduledThreadPool源码：super(corePoolSize, Integer.MAX_VALUE,DEFAULT_KEEPALIVE_MILLIS,
 * MILLISECONDS,new DelayedWorkQueue());
 * 周期线程池：指定延时之后执行或者固定的频率周期性的执行提交任务
 * ScheduledExecutorService
 * 核心线程数：corePoolSize = corePoolSize、最大线程数：maximumPoolSize = Integer.MAX_VALUE、非核心线程生命时间：keepAliveTime = MAX_VALUE、阻塞队列：DelayedWorkQueue，有序队列，按照距离下次执行时间间隔长短排序；
 * 1.只有固定的核心线程数量（corePoolSize = nThreads，DelayedWorkQueue无界时间间隔优先队列，理论上不会满，故不会有最大线程数，故非核心线程生命时间：keepAliveTime = MAX_VALUE也没有意义）
 * 2.核心线程不会被回收，如果后续有任务执行，会继续复用；
 * 参考：https://segmentfault.com/a/1190000015190796
 */
class ScheduleThreadPoolTest {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss", Locale.CHINA);

    public static void main1(String[] args) {
        ScheduledExecutorService scheduledExecutorService = null;
        try {
            //currentTime = 01:25:15，程序开始执行...
            //currentTime = 01:25:20，第 1 Callable，使用线程pool-1-thread-1开始执行....//5秒之后执行任务
            //currentTime = 01:25:23，第 1 Callable，使用线程pool-1-thread-1执行完毕----//3秒之后执行完毕
            //currentTime = 01:25:15，result = success//执行call是异步的，故01:25:15和程序执行时间相同，就开始等待结果：result = success
            //currentTime = 01:25:23，scheduledExecutorService  shutdown//01:25:23 Callable执行完毕返回结果，继续向后执行
            System.out.println("currentTime = " + simpleDateFormat.format(new Date()) + "，程序开始执行...");
            scheduledExecutorService = Executors.newScheduledThreadPool(Runtime
                    .getRuntime().availableProcessors(), new MyThreadFactory());
            //在指定delay(5秒)之后，执行提交的Runable(MyRunnable)，返回一个ScheduledFuture
            ScheduledFuture<String> scheduledFuture = scheduledExecutorService.schedule(new MyCallable(1, 3000),
                    5, TimeUnit.SECONDS);
            //通过scheduledFuture.get()返回执行结果，等待返回结果
            System.out.println("currentTime = " + simpleDateFormat.format(new Date()) + "，result = " + scheduledFuture.get());
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("currentTime = " + simpleDateFormat.format(new Date()) + "，e = " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (scheduledExecutorService != null) {
                System.out.println("currentTime = " + simpleDateFormat.format(new Date()) + "，scheduledExecutorService  shutdown");
                scheduledExecutorService.shutdown();
            }
        }
    }

    public static void main2(String[] args) {
        ScheduledExecutorService scheduledExecutorService = null;
        try {
            //currentTime = 01:43:23，程序开始执行...
            //currentTime = 01:43:23，is cancel = false//没有取消
            //currentTime = 01:43:23，is done = false//没有结束
            //currentTime = 01:43:25，e = null//最多等待2秒，Callable还没返回
            //currentTime = 01:43:25，scheduledExecutorService  shutdown
            //java.util.concurrent.TimeoutException//处理异常执行finally
            //	at java.util.concurrent.FutureTask.get(FutureTask.java:205)
            //	at com.java.xknowledge.se.thread.threadpool.executors.ScheduleThreadPoolTest.main(ScheduleThreadPoolTest.java:74)
            //currentTime = 01:43:28，第 1 Callable，使用线程pool-1-thread-1开始执行....//shudown并不是强制结束
            //currentTime = 01:43:31，第 1 Callable，使用线程pool-1-thread-1执行完毕----
            System.out.println("currentTime = " + simpleDateFormat.format(new Date()) + "，程序开始执行...");
            scheduledExecutorService = Executors.newScheduledThreadPool(Runtime
                    .getRuntime().availableProcessors(), new MyThreadFactory());
            //在指定delay(5秒)之后，执行提交的Runable(MyRunnable)，返回一个ScheduledFuture
            ScheduledFuture<String> scheduledFuture = scheduledExecutorService.schedule(new MyCallable(1, 3000),
                    5, TimeUnit.SECONDS);
            //通过scheduledFuture.get()返回执行结果，等待返回结果
            System.out.println("currentTime = " + simpleDateFormat.format(new Date()) + "，is cancel = " + scheduledFuture.isCancelled());
            System.out.println("currentTime = " + simpleDateFormat.format(new Date()) + "，is done = " + scheduledFuture.isDone());
            System.out.println("currentTime = " + simpleDateFormat.format(new Date()) + "，result = " + scheduledFuture.get(2000, TimeUnit.MILLISECONDS));
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            System.out.println("currentTime = " + simpleDateFormat.format(new Date()) + "，e = " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (scheduledExecutorService != null) {
                System.out.println("currentTime = " + simpleDateFormat.format(new Date()) + "，scheduledExecutorService  shutdown");
                scheduledExecutorService.shutdown();
//                scheduledExecutorService.shutdownNow();//就立马结束，不会继续执行线程池中的任务
            }
        }
    }

    public static void main3(String[] args) {
        System.out.println("currentTime = " + simpleDateFormat.format(new Date()) + "，程序开始执行...");
        //currentTime = 01:51:19，程序开始执行...
        //currentTime = 01:51:24，第 1 Runnable，使用线程pool-1-thread-1开始执行....//5秒之后，开始执行
        //currentTime = 01:51:27，第 1 Runnable，使用线程pool-1-thread-1执行完毕----
        //currentTime = 01:51:27，第 1 Runnable，使用线程pool-1-thread-1开始执行....//每3秒重复执行
        //currentTime = 01:51:30，第 1 Runnable，使用线程pool-1-thread-1执行完毕----
        //currentTime = 01:51:30，第 1 Runnable，使用线程pool-1-thread-2开始执行....
        //currentTime = 01:51:33，第 1 Runnable，使用线程pool-1-thread-2执行完毕----
        //currentTime = 01:51:33，第 1 Runnable，使用线程pool-1-thread-1开始执行....
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(Runtime
                .getRuntime().availableProcessors(), new MyThreadFactory());
        //提交一个Runnable任务，延迟5秒，开始周期性3秒执行（上一个开始执行3秒后，又开始新的）
        scheduledExecutorService.scheduleAtFixedRate(new MyRunnable(1, 3000), 5, 3, TimeUnit.SECONDS);
    }

    public static void main(String[] args) {
        System.out.println("currentTime = " + simpleDateFormat.format(new Date()) + "，程序开始执行...");
        //currentTime = 01:53:37，程序开始执行...
        //currentTime = 01:53:42，第 1 Runnable，使用线程pool-1-thread-1开始执行....//5秒之后开始执行
        //currentTime = 01:53:45，第 1 Runnable，使用线程pool-1-thread-1执行完毕----
        //currentTime = 01:53:48，第 1 Runnable，使用线程pool-1-thread-1开始执行....//上一个任务执行完毕在过3秒开始执行
        //currentTime = 01:53:51，第 1 Runnable，使用线程pool-1-thread-1执行完毕----
        //currentTime = 01:53:54，第 1 Runnable，使用线程pool-1-thread-2开始执行....
        //currentTime = 01:53:57，第 1 Runnable，使用线程pool-1-thread-2执行完毕----
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(Runtime
                .getRuntime().availableProcessors(), new MyThreadFactory());
        //提交一个Runnable任务，延迟5秒，开始周期性3秒执行（上一个执行完毕3秒后，又开始新的）
        scheduledExecutorService.scheduleWithFixedDelay(new MyRunnable(1, 3000), 5, 3, TimeUnit.SECONDS);
    }
}
