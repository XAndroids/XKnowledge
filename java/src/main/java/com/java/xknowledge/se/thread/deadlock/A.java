package com.java.xknowledge.se.thread.deadlock;

class A {
    //调用a.foo()方法，获取a对象为线程锁
    public synchronized void foo(B b) {
        System.out.println("当前线程名称:" + Thread.currentThread().getName() + "进入实例A的foot方法");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("当前线程名称:" + Thread.currentThread().getName() + "企图访问B实例的last方法");
        //调用b.last方法，尝试获取b对象为线程锁
        b.last();
    }

    public synchronized void last() {
        System.out.println("进入A类的last的方法内部");
    }
}
