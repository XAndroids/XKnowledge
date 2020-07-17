package com.java.xknowledge.design.singleton;

/**
 * 饿汉式-对象锁，双重判断
 * 双重检查，避免大部分对象锁的性能消耗
 */
public class Singleton04 {
    private static Singleton04 INSTANCE;

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
