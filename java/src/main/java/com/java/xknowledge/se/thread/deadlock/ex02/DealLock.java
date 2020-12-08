package com.java.xknowledge.se.thread.deadlock.ex02;

/**
 * 线程死锁演示：
 * method1先拿object1，再拿object2
 * method2先拿object2，再拿object1
 * 容易造成，在两个线程中执行method1、method2时，线程1:method1持有锁object1，等待method2释放object2，而线程2:
 * method2持有锁object2，等待method1释放object1，故会造成死锁！！
 * 运行：
 * Second获取object1锁.
 * Main获取object2锁.
 */
class DealLock {
    private static final Object object1 = new Object();//synchronized代码块对象锁1
    private static final Object object2 = new Object();//synchronized代码块对象锁2

    //method1先拿object1，再拿object2
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

    //method2先拿object2，再拿object1
    public static void method2() throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        synchronized (object2) {
            System.out.println(threadName + "获取object2锁.");
            Thread.sleep(1000);
            synchronized (object1) {
                System.out.println(threadName + "获取object1锁.");
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
