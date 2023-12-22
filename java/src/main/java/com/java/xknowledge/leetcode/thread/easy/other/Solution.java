package com.java.xknowledge.leetcode.thread.easy.other;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 两个线程从0-100交替打印
 * 链接：https://www.136.la/tech/show-1455159.html
 */
class Solution {
    //两个线程共同输出的数值，静态线程间共享
    private static int outNumber = 1;
    //线程间对象锁
    private static final Object lockObject = new Object();

    /**
     * 解法一：notify和wait方式
     * 运行：线程1和线程2，交替后去锁执行输出
     * Thread-0,outNumber = 1
     * Thread-1,outNumber = 2
     * Thread-0,outNumber = 3
     * Thread-1,outNumber = 4
     * Thread-0,outNumber = 5
     * Thread-1,outNumber = 6
     * Thread-0,outNumber = 7
     * Thread-1,outNumber = 8
     * Thread-0,outNumber = 9
     */
    static class MyThread extends Thread {
        @Override
        public void run() {
            while (outNumber <= 100) {
                //每次输出前synchronized争抢锁
                synchronized (lockObject) {
                    System.out.println(Thread.currentThread().getName() + ",outNumber = " + outNumber);
                    outNumber++;

                    //synchronized方法快完成后释放锁，notify唤醒另一个线程争抢锁
                    lockObject.notify();

                    try {
                        //当前线程wait()阻塞
                        lockObject.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 解法二：synchronized方式
     * 运行：不会出现线程交替获取锁的情况，但是通过奇数偶数条件，能做到交替输出
     * Thread-0
     * Thread-0
     * Thread-1
     * Thread-1,outNumber = 1
     * Thread-1
     * Thread-1
     * Thread-0
     * Thread-0,outNumber = 2
     * Thread-0
     * Thread-0
     */
    static class MyThread21 extends Thread {
        @Override
        public void run() {
            while (outNumber <= 100) {
                synchronized (lockObject) {
                    System.out.println(Thread.currentThread().getName());
                    if (outNumber % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + ",outNumber = " + outNumber);
                        outNumber++;
                    }
                }
            }
        }
    }

    static class MyThread22 extends Thread {
        @Override
        public void run() {
            while (outNumber <= 100) {
                synchronized (lockObject) {
                    System.out.println(Thread.currentThread().getName());
                    if (outNumber % 2 == 1) {
                        System.out.println(Thread.currentThread().getName() + ",outNumber = " + outNumber);
                        outNumber++;
                    }
                }
            }
        }
    }

    /**
     * 方式3：Lock方式
     * 运行：不会出现线程交替获取锁的情况，但是通过奇数偶数条件，能做到交替输出
     * Thread-0
     * Thread-0
     * Thread-1
     * Thread-1,outNumber = 1
     * Thread-1
     * Thread-1
     * Thread-0
     * Thread-0,outNumber = 2
     * Thread-0
     * Thread-0
     */
    private static ReentrantLock reentrantLock = new ReentrantLock();

    static class MyThread31 extends Thread {
        @Override
        public void run() {
            while (outNumber <= 100) {
                reentrantLock.lock();

                System.out.println(Thread.currentThread().getName());
                if (outNumber % 2 == 0) {
                    System.out.println(Thread.currentThread().getName() + ",outNumber = " + outNumber);
                    outNumber++;
                }

                reentrantLock.unlock();
            }
        }
    }

    static class MyThread32 extends Thread {
        @Override
        public void run() {
            while (outNumber <= 100) {
                reentrantLock.lock();

                System.out.println(Thread.currentThread().getName());
                if (outNumber % 2 == 1) {
                    System.out.println(Thread.currentThread().getName() + ",outNumber = " + outNumber);
                    outNumber++;
                }

                reentrantLock.unlock();
            }
        }
    }

    /**
     * Lock+Condition方式，同wait()和notify()方式
     */
    private static ReentrantLock reentrantLock2 = new ReentrantLock();
    private static Condition condition = reentrantLock2.newCondition();

    static class MyThread4 extends Thread {

        @Override
        public void run() {
            while (outNumber <= 100) {
                reentrantLock2.lock();
                System.out.println(Thread.currentThread().getName() + ",outNumber = " + outNumber);
                outNumber++;

                condition.signal();
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                reentrantLock2.unlock();
            }
        }
    }


    public static void main(String[] args) {
        MyThread thread1 = new MyThread();
        MyThread thread2 = new MyThread();
        thread1.start();
        thread2.start();
    }
}
