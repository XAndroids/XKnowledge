package com.java.xknowledge.design.create.singleton.other;

/**
 * 饿汉式-对象锁，双重判断
 * 双重检查，避免大部分对象锁的性能消耗
 */
public class Singleton04 {
    //JIT指令重排，在没有初始返回instance
    //参考：https://juejin.im/post/5c92e5b45188252d64582700
    private static volatile Singleton04 INSTANCE;

    private Singleton04() {

    }

    public static Singleton04 getInstance() {
        if (INSTANCE == null) {
            synchronized (Singleton04.class) {
                if (INSTANCE == null) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    INSTANCE = new Singleton04();
                }
                return INSTANCE;
            }
        }
        return INSTANCE;
    }
}
