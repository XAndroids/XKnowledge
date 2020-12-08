package com.java.xknowledge.se.thread.deadlock.ex01;

class B {
    //调用b.bar()方法，获取b对象为线程锁
    public synchronized void bar(A a) {
        System.out.println("当前线程名称:" + Thread.currentThread().getName() + "进入B实例的bar方法");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("当前线程名称:" + Thread.currentThread().getName() + "企图访问A实例的last方法");
        //调用a.last方法，尝试获取a对象为线程锁
        a.last();
    }

    public synchronized void last() {
        System.out.println("进入B类的last的方法内部");
    }
}
