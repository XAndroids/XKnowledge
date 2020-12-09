package com.java.xknowledge.se.thread.deadlock.ex02;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程死锁解决办法：尝试拿锁，解决死锁问题
 * 使用ReentrantLock，trylock()尝试拿锁，如果没有拿到则unlock()释放锁，重新尝试拿避免死锁
 * 运行：
 * 随机睡眠避免活锁：
 * 运行：
 */
class DealLock02 {
    private static final Lock object1 = new ReentrantLock();
    private static final Lock object2 = new ReentrantLock();

    //方法一先拿object1，在拿object2锁
    private static void method1() throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        Random random = new Random();
        while (true) {
            System.out.println(threadName + "尝试获取object1锁.");
            if (object1.tryLock()) {
                try {
                    System.out.println(threadName + "获取object1锁.");
                    System.out.println(threadName + "尝试获取object2锁.");
                    if (object2.tryLock()) {
                        try {
                            System.out.println(threadName + "获取object2锁!!!!!");
                        } finally {
                            System.out.println(threadName + "释放object2锁.");
                            object2.unlock();
                        }
                    }
                } finally {
                    System.out.println(threadName + "释放object1锁.");
                    object1.unlock();
                }
            }
            Thread.sleep(random.nextInt(3));//随机睡眠，避免活锁问题！！！
        }
    }

    //方法二也是先拿object1，在拿object2
    public static void method2() throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        Random random = new Random();
        while (true) {
            System.out.println(threadName + "尝试获取object2锁.");
            if (object2.tryLock()) {
                try {
                    System.out.println(threadName + "获取object2锁.");
                    System.out.println(threadName + "尝试获取object1锁.");
                    if (object1.tryLock()) {
                        try {
                            System.out.println(threadName + "获取object1锁!!!!!!");
                        } finally {
                            System.out.println(threadName + "释放object1锁.");
                            object1.unlock();
                        }
                    }
                } finally {
                    System.out.println(threadName + "释放object2锁.");
                    object2.unlock();
                }
            }
            Thread.sleep(random.nextInt(3));//随机睡眠，避免活锁问题！！！
        }
    }

    private static class SecondThread extends Thread {
        private String name;

        public SecondThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            Thread.currentThread().setName(name);
            try {
                method2();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread.currentThread().setName("Main");
        SecondThread secondThread = new SecondThread("Second");
        secondThread.start();
        method1();
    }
}
