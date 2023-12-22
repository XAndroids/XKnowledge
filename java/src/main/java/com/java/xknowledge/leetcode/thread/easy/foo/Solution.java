package com.java.xknowledge.leetcode.thread.easy.foo;

/**
 * 1114. 按序打印
 * 链接：https://leetcode-cn.com/problems/print-in-order/
 */
class Solution {
    static class Foo {
        private int index = 1;
        private final Object lock = new Object();

        public Foo() {

        }

        public void first() throws InterruptedException {
            synchronized (lock) {//获取lock锁
                if (index == 1) {//判断当前条件，是否适合输出，输出完毕后释放锁
                    System.out.println("first");
                    index++;
                    lock.notifyAll();
                }
            }
        }

        public void second() throws InterruptedException {
            synchronized (lock) {//获取lock锁
                while (index < 2) {//判断当前条件是否适合输出，or否则释放锁等待
                    System.out.println("second wait");
                    lock.wait();
                }

                System.out.println("second");
                index++;
                lock.notifyAll();
            }
        }

        public void third() throws InterruptedException {
            synchronized (lock) {//获取lock锁
                while (index < 3) {//判断当前条件是否适合输出，or否则释放锁等待
                    System.out.println("third wait");
                    lock.wait();
                }

                System.out.println("third");
                index++;
            }
        }
    }

    static class A extends Thread {
        @Override
        public void run() {
            try {
                foo.first();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class B extends Thread {
        @Override
        public void run() {
            try {
                foo.second();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class C extends Thread {
        @Override
        public void run() {
            try {
                foo.third();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static final Foo foo = new Foo();

    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        C c = new C();
        b.start();
        c.start();
        a.start();
    }
}
