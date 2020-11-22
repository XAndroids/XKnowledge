package com.java.xknowledge.se.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * volatile关键字，一旦共享变量（成员变量、静态成员变量被volatile修饰后：
 * 1.保证不同线程进行操作时的可见性，即一个线程修改变量时，这新值对其它线程来说是立即可见的
 * 2.禁止指令重排
 * 参考：https://www.cnblogs.com/dolphin0520/p/3920373.html
 */
class VolatileTest {
    //带有volatile关键字，在多个线程同时进行读写时，轻质将修改不的值立即写入主内存；
    //并通知其它线程的工作内存无效，读取时重新从主内存读取最新值
    public volatile int inc = 0;

    public int getInc() {
        return inc;
    }

    public void setInc(int inc) {
        this.inc = inc;
    }

    public static void main(String[] args) {
        //volatile关键字保证可见性问题：
        //第0、5、10....线程进行共享成员变量i进行写入，其它线程进行读取
        //如果不加volatile关键字，由于Java内存模型，线程-10在工作内容修改10的内容，并没有立即写回主内存，此时线程-11读取的还是5。即存在多线程安全-可见性问题
        //Thread = Thread-8, getinc = 5
        //Thread = Thread-10, setInc = 10  ---------
        //Thread = Thread-11, getinc = 5
        //Thread = Thread-12, getinc = 10
        final VolatileTest volatileTest = new VolatileTest();
        for (int i = 0; i < 100; i++) {
            if (i % 5 == 0) {
                final int finalI = i;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Thread = " + Thread.currentThread().getName() + ", setInc = "
                                + finalI + "  ---------");
                        volatileTest.setInc(finalI);
                    }
                }).start();
            } else {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Thread = " + Thread.currentThread().getName() + ", getinc = "
                                + volatileTest.getInc());
                    }
                }).start();
            }
        }
    }

    public AtomicInteger atomInc = new AtomicInteger(0);
    Lock lock = new ReentrantLock();

    //除了x = 10直接复制操作是原子性操作，inc++，inc = inc + 1等都不是原子性操作，volatile无法保证该操作的线程安全！！
    //私用synchronized关键字保证原子性
    public synchronized void increase() {
        inc++;
    }

    //使用ReentrantLock保证原子性
    public void increase2() {
        lock.lock();
        try {
            inc = inc + 1;
        } finally {
            lock.unlock();
        }
    }

    //使用AtomicInteger类型保证原子性
    public void increase3() {
        atomInc.getAndIncrement();
    }

    public static void main1(String[] args) {
        //volatile关键字无法保证原子性问题：
        //虽然volatile修饰inc，但是由于它无法保证原子性，故在多个线程进行该变量的非原子性操作如，inc++,inc = inc+1;等，即存在多线程安全-原子性问题;
        //volatileTest2 = 9856
        //可以采用AtomicInteger类型，或加锁如：synchronized关键字和ReentrantLock对象锁
        long start = System.currentTimeMillis();
        final VolatileTest volatileTest2 = new VolatileTest();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        volatileTest2.increase3();
                    }
                }
            }).start();
        }

        //保证前面的线程都执行完
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        System.out.println("volatileTest2 = " + volatileTest2.atomInc.get());
        long end = System.currentTimeMillis();
        System.out.println("over time = " + (end - start));
    }

    public static volatile boolean isInit;

    public void init() {
        if (!isInit) {
            //模拟初始化耗时操作
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread = " + Thread.currentThread().getName() + ", isInit = " + isInit);
            isInit = true;
        }
    }

    public static void main2(String[] args){
        //volatile关键字修饰isInit，并不能保证线程安全-原子性，在符合操作情况下如isinit()还是无法保证线程安全
        //Thread = Thread-0, isInit = false
        //Thread = Thread-5, isInit = true
        //Thread = Thread-7, isInit = true
        //Thread = Thread-1, isInit = false
        //Thread = Thread-2, isInit = false
        //Thread = Thread-3, isInit = false
        final VolatileTest volatileTest3 = new VolatileTest();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    volatileTest3.init();
                }
            }).start();
        }
    }
}
