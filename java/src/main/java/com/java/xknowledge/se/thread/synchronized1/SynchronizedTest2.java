package com.java.xknowledge.se.thread.synchronized1;

/**
 * Synchronized可重入性，一个线程得到一个对象锁后再次请求该对象锁
 * 运行：
 * i:2000000
 * j:2000000
 * <p>
 * Process finished with exit code 0
 * 参考：https://www.cnblogs.com/duanxz/p/4489367.html
 */
class SynchronizedTest2 implements Runnable {
    static SynchronizedTest2 instance = new SynchronizedTest2();
    static int i = 0;
    static int j = 0;

    @Override
    public void run() {
        for (int j = 0; j < 1000000; j++) {
            // this,当前实例对象锁
            synchronized (this) {
                i++;
                increase();// synchronized方法，再次申请该Runnable对象锁，即可重入性能
            }
        }
    }

    public synchronized void increase() {
        j++;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("i:" + i);
        System.out.println("j:" + j);

    }
}
