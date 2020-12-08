package com.java.xknowledge.se.thread.deadlock.ex02;

/**
 * 线程死锁解决办法：顺序拿锁
 * method1先拿object1，再拿object2
 * method2也先拿object1，再拿object2
 * 就不会造成相互等待死锁的情况。
 * 运行：
 * Main获取object2锁.
 * Main获取object1锁.
 * Second获取object1锁.
 * Second获取object2锁.
 *
 * Process finished with exit code 0
 * FIXME 但是这种顺序拿锁方案，业务场景能支持吗？？？
 */
class DealLock01 {
    private static final Object object1 = new Object();//synchronized代码块对象锁1
    private static final Object object2 = new Object();//synchronized代码块对象锁2

    //方法一先拿object1，在拿object2锁
    private static void method1() throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        synchronized (object1) {
            System.out.println(threadName + "获取object1锁.");
            Thread.sleep(1000);
            synchronized (object2) {
                System.out.println(threadName + "获取object2锁.");
            }
        }
    }

    //方法二也是先拿object1，在拿object2
    public static void method2() throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        synchronized (object1) {
            System.out.println(threadName + "获取object1锁.");
            Thread.sleep(1000);
            synchronized (object2) {
                System.out.println(threadName + "获取object2锁.");
            }
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
                method1();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread.currentThread().setName("Main");
        SecondThread secondThread = new SecondThread("Second");
        secondThread.start();
        method2();
    }
}
