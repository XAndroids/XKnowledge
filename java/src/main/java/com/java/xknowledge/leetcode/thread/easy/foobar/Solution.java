package com.java.xknowledge.leetcode.thread.easy.foobar;

/**
 * 1115. 交替打印FooBar
 * 链接：https://leetcode-cn.com/problems/print-foobar-alternately/
 */
class Solution {
    static class FooBar {
        private int n;
        private int index = 0;

        public FooBar(int n) {
            this.n = n;
        }

        public void foo(Runnable printFoo) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                synchronized (FooBar.class) {
                    //为了保证foo一定在bar前面，则使用index奇偶数输出
                    //否则只能保证先后，可能出现：bar foobar foo的case，不满足需求
                    if (index % 2 != 0) {
                        FooBar.class.wait();
                    }

                    // printFoo.run() outputs "foo". Do not change or remove this line.
                    printFoo.run();
                    index++;

                    FooBar.class.notify();
                }
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                synchronized (FooBar.class) {
                    if (index % 2 == 0) {
                        FooBar.class.wait();
                    }

                    // printBar.run() outputs "bar". Do not change or remove this line.
                    printBar.run();
                    index++;

                    FooBar.class.notify();
                }
            }
        }

        private static final FooBar fooBar = new FooBar(10);

        static class Thread1 extends Thread {
            @Override
            public void run() {
                try {
                    fooBar.foo(() -> System.out.print("foo"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        static class Thread2 extends Thread {
            @Override
            public void run() {
                try {
                    fooBar.bar(() -> System.out.println("bar"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public static void main(String[] args) {
            Thread1 thread1 = new Thread1();
            Thread2 thread2 = new Thread2();
            thread2.start();
            thread1.start();
        }
    }
}
