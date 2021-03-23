package com.java.xknowledge.se.thread.synchronized1;

/**
 * Synchronized可重入性，子类继承父类时，可通过重入锁调用父类的方法
 * 运行：
 * i:2000000
 * j:0
 * <p>
 * Process finished with exit code 0
 * 参考：https://www.cnblogs.com/duanxz/p/4489367.html
 */
class SynchronizedTest3 extends SynchronizedTest2 {
    static SynchronizedTest3 instanceImpl = new SynchronizedTest3();

    public synchronized void reduce() {
        j--;
        increase();
    }

    @Override
    public void run() {
        for (int j = 0; j < 1000000; j++) {
            // this,当前实例对象锁
            synchronized (this) {
                i++;
                reduce();// synchronized的可重入性
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instanceImpl);
        Thread t2 = new Thread(instanceImpl);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("i:" + i);
        System.out.println("j:" + j);
    }
}
