package com.java.xknowledge.se.thread.synchronized1;

/**
 * Synchronized关键字测试类
 * 使用同步代码块，和同步方法实现线程同步
 * 参考《享学2：深入理解并发编程和归纳总结02》
 */
class SynchronizedTest {

    static class Count {
        private long count = 0;

        //使用synchronized同步方法
        public synchronized void addCount() {
            count = count + 1;
        }

        //使用synchronized同步代码块
        public void addCount2() {
            synchronized (this) {
                count = count + 1;
            }
        }
    }

    static class CountThread extends Thread {
        private Count count;

        public CountThread(Count count) {
            this.count = count;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                count.addCount();
                count.addCount2();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Count count = new Count();
        //启动两个线程
        CountThread countThread = new CountThread(count);
        CountThread countThread1 = new CountThread(count);
        countThread.start();
        countThread1.start();
        Thread.sleep(50);
        System.out.println(count.count);//=20000？
    }
}

