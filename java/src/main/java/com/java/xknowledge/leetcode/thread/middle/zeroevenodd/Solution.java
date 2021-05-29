package com.java.xknowledge.leetcode.thread.middle.zeroevenodd;

/**
 * 1116. 打印零与奇偶数
 * 链接：https://leetcode-cn.com/problems/print-zero-even-odd/
 */
class Solution {
    static class ZeroEvenOdd {
        private int n;
        private final Object lock = new Object();
        private int index = 1;

        public ZeroEvenOdd(int n) {
            this.n = n;
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void zero() throws InterruptedException {
            //如果是偶数，则等待
            synchronized (lock) {
                while (index <= n * 2) {
                    if (index % 2 == 0) {
                        lock.wait();//每一次wait()等待唤醒后，都重新判断条件
                    } else {
                        System.out.print("0");
                        index++;
                        lock.notifyAll();
                    }
                }
            }
        }

        public void even() throws InterruptedException {
            synchronized (lock) {
                while (index <= n * 2) {
                    if (index % 2 != 0 || (index / 2) % 2 == 0) {
                        lock.wait();//每一次wait()等待唤醒后，都重新判断条件
                    } else {
                        System.out.print(index / 2);
                        index++;
                        lock.notifyAll();
                    }
                }
            }
        }

        public void odd() throws InterruptedException {
            synchronized (lock) {
                while (index <= n * 2) {
                    if (index % 2 != 0 || (index / 2) % 2 != 0) {
                        lock.wait();//每一次wait()等待唤醒后，都重新判断条件
                    } else {
                        System.out.print(index / 2);
                        index++;
                        lock.notifyAll();
                    }
                }
            }
        }

        private static ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(5);

        static class Thread1 extends Thread {
            @Override
            public void run() {
                try {
                    zeroEvenOdd.zero();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        static class Thread2 extends Thread {
            @Override
            public void run() {
                try {
                    zeroEvenOdd.even();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        static class Thread3 extends Thread {
            @Override
            public void run() {
                try {
                    zeroEvenOdd.odd();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

        public static void main(String[] args) {
            Thread1 thread1 = new Thread1();
            Thread2 thread2 = new Thread2();
            Thread3 thread3 = new Thread3();
            thread1.start();
            thread2.start();
            thread3.start();
        }
    }
}
