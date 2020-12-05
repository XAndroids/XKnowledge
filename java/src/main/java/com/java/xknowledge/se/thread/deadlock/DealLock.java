package com.java.xknowledge.se.thread.deadlock;

/**
 * 线程死锁实践
 * 当前线程名称:副线程进入B实例的bar方法
 * 当前线程名称:主线程进入实例A的foot方法
 * 当前线程名称:副线程企图访问A实例的last方法
 * 当前线程名称:主线程企图访问B实例的last方法
 * 参考《疯狂的Java讲义》线程
 */
class DealLock implements Runnable {
    private A a = new A();
    private B b = new B();

    public void init() {
        Thread.currentThread().setName("主线程");
        //调用a对象的foo方法，主线程获取a对象的锁，希望获取b对象锁
        a.foo(b);
        System.out.println("进入主线程之后");
    }

    @Override
    public void run() {
        Thread.currentThread().setName("副线程");
        //调用b对象的bar方法，副线持有b对象锁，希望获取a对象的锁
        b.bar(a);
        System.out.println("进入副线程之后");
    }

    public static void main(String[] args) {
        DealLock dealLock = new DealLock();
        new Thread(dealLock).start();
        dealLock.init();
    }
}
