package com.java.xknowledge.se.thread.threadpool.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Executors.newCachedThreadPool测试类：
 * Executors类扮演线程池工厂的角色，通过Executors可以取得一个拥有特定功能的线程池。
 * CachedThreadPool线程池：
 * 源码：ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>())
 * 可缓存线程池：核心线程数：corePoolSize = 0、最大线程数：maximumPoolSize = Integer.MAX_VALUE、非核心线程生命时间：keepAliveTime = 60s、阻塞队列：SynchronousQueue
 *   1.不对线程池大小做限制（Integer.MAX_VALUE）
 *   2.任务增加时，当前线程数量无法满足时，会立即添加（同步队列不阻塞立即交付）新线程来处理任务（corPoolSize = 0,SynchronousQueue：没有存储功能，直接交付工作，降低将数据从生产者移动到消费者延迟）
 *   3.超过60S，空闲线程就会回收；
 * 参考：
 * SynchronousQueue：https://www.jianshu.com/p/b7f7eb2bc778
 * https://blog.csdn.net/GS_MY/article/details/38677661
 * https://blog.csdn.net/GS_MY/article/details/38677661
 */
class CachedThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool(new MyThreadFactory());
        for (int i = 0; i < 60; i++) {
            cachedThreadPool.execute(new MyRunnable(i));
            //第一次执行前20个任务，创建线程1、2、3、4、5、6
            //currentTime = 1605811439948，第 1 Runnable，使用线程pool-1-thread-2正在执行
            //currentTime = 1605811439948，第 0 Runnable，使用线程pool-1-thread-1正在执行
            //currentTime = 1605811439949，第 2 Runnable，使用线程pool-1-thread-3正在执行
            //currentTime = 1605811439949，第 3 Runnable，使用线程pool-1-thread-4正在执行
            //currentTime = 1605811439949，第 4 Runnable，使用线程pool-1-thread-4正在执行
            //currentTime = 1605811439949，第 5 Runnable，使用线程pool-1-thread-3正在执行
            //currentTime = 1605811439949，第 7 Runnable，使用线程pool-1-thread-1正在执行
            //currentTime = 1605811439949，第 6 Runnable，使用线程pool-1-thread-4正在执行
            //currentTime = 1605811439949，第 8 Runnable，使用线程pool-1-thread-2正在执行
            //currentTime = 1605811439949，第 10 Runnable，使用线程pool-1-thread-1正在执行
            //currentTime = 1605811439949，第 11 Runnable，使用线程pool-1-thread-2正在执行
            //currentTime = 1605811439950，第 14 Runnable，使用线程pool-1-thread-3正在执行
            //currentTime = 1605811439950，第 12 Runnable，使用线程pool-1-thread-2正在执行
            //currentTime = 1605811439951，第 15 Runnable，使用线程pool-1-thread-5正在执行
            //currentTime = 1605811439952，第 17 Runnable，使用线程pool-1-thread-2正在执行
            //currentTime = 1605811439952，第 18 Runnable，使用线程pool-1-thread-2正在执行
            //currentTime = 1605811439954，第 20 Runnable，使用线程pool-1-thread-6正在执行
            //currentTime = 1605811439957，第 9 Runnable，使用线程pool-1-thread-4正在执行
            //currentTime = 1605811439957，第 16 Runnable，使用线程pool-1-thread-5正在执行
            //currentTime = 1605811439957，第 13 Runnable，使用线程pool-1-thread-1正在执行
            //currentTime = 1605811439957，第 19 Runnable，使用线程pool-1-thread-3正在执行
            if (i == 20) {
                //35秒之后，原来创建的线程1、2、3、4、5、6还未销毁继续复用，由于新任务执行，当前线程不足新创建7、8、9、10线程
                //currentTime = 1605811474992，第 22 Runnable，使用线程pool-1-thread-1正在执行
                //currentTime = 1605811474992，第 25 Runnable，使用线程pool-1-thread-6正在执行
                //currentTime = 1605811474992，第 24 Runnable，使用线程pool-1-thread-4正在执行
                //currentTime = 1605811474992，第 28 Runnable，使用线程pool-1-thread-6正在执行
                //currentTime = 1605811474992，第 23 Runnable，使用线程pool-1-thread-5正在执行
                //currentTime = 1605811474992，第 33 Runnable，使用线程pool-1-thread-5正在执行
                //currentTime = 1605811474992，第 31 Runnable，使用线程pool-1-thread-6正在执行
                //currentTime = 1605811474992，第 21 Runnable，使用线程pool-1-thread-3正在执行
                //currentTime = 1605811474992，第 30 Runnable，使用线程pool-1-thread-8正在执行
                //currentTime = 1605811474993，第 36 Runnable，使用线程pool-1-thread-6正在执行
                //currentTime = 1605811474992，第 32 Runnable，使用线程pool-1-thread-4正在执行
                //currentTime = 1605811474992，第 27 Runnable，使用线程pool-1-thread-7正在执行
                //currentTime = 1605811474993，第 38 Runnable，使用线程pool-1-thread-10正在执行
                //currentTime = 1605811474992，第 29 Runnable，使用线程pool-1-thread-1正在执行
                //currentTime = 1605811474992，第 26 Runnable，使用线程pool-1-thread-2正在执行
                //currentTime = 1605811474993，第 40 Runnable，使用线程pool-1-thread-4正在执行
                //currentTime = 1605811474993，第 39 Runnable，使用线程pool-1-thread-6正在执行
                //currentTime = 1605811474993，第 37 Runnable，使用线程pool-1-thread-5正在执行
                //currentTime = 1605811474993，第 34 Runnable，使用线程pool-1-thread-9正在执行
                //currentTime = 1605811474993，第 35 Runnable，使用线程pool-1-thread-3正在执行
                try {
                    Thread.sleep(35000);
                    System.out.println("--------------------------------");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (i == 40) {
                //65秒之后，原来创建的线程1、2、3、4、5、6、7、8、9、10都销毁了，后续重新创建11、12、13、14、15、16线程执行
                //currentTime = 1605811540001，第 41 Runnable，使用线程pool-1-thread-11正在执行
                //currentTime = 1605811540001，第 42 Runnable，使用线程pool-1-thread-12正在执行
                //currentTime = 1605811540001，第 44 Runnable，使用线程pool-1-thread-11正在执行
                //currentTime = 1605811540001，第 43 Runnable，使用线程pool-1-thread-13正在执行
                //currentTime = 1605811540001，第 45 Runnable，使用线程pool-1-thread-12正在执行
                //currentTime = 1605811540001，第 46 Runnable，使用线程pool-1-thread-11正在执行
                //currentTime = 1605811540001，第 48 Runnable，使用线程pool-1-thread-11正在执行
                //currentTime = 1605811540001，第 47 Runnable，使用线程pool-1-thread-14正在执行
                //currentTime = 1605811540001，第 49 Runnable，使用线程pool-1-thread-12正在执行
                //currentTime = 1605811540001，第 50 Runnable，使用线程pool-1-thread-13正在执行
                //currentTime = 1605811540001，第 52 Runnable，使用线程pool-1-thread-13正在执行
                //currentTime = 1605811540002，第 51 Runnable，使用线程pool-1-thread-15正在执行
                //currentTime = 1605811540002，第 55 Runnable，使用线程pool-1-thread-11正在执行
                //currentTime = 1605811540002，第 53 Runnable，使用线程pool-1-thread-12正在执行
                //currentTime = 1605811540002，第 58 Runnable，使用线程pool-1-thread-15正在执行
                //currentTime = 1605811540002，第 59 Runnable，使用线程pool-1-thread-13正在执行
                //currentTime = 1605811540002，第 54 Runnable，使用线程pool-1-thread-14正在执行
                //currentTime = 1605811540002，第 56 Runnable，使用线程pool-1-thread-16正在执行
                //currentTime = 1605811540002，第 57 Runnable，使用线程pool-1-thread-11正在执行
                try {
                    Thread.sleep(65000);
                    System.out.println("--------------------------------");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        cachedThreadPool.shutdown();
    }
}
