package com.java.xknowledge.design.singleton;

/**
 * 饿汉式-方法锁
 * 通过synchronized解决了线程安全问题，但是由于线程锁效率降低
 */
public class Singleton03 {
    private static Singleton03 INSTANCE;

    private Singleton03() {

    }

    public synchronized static Singleton03 getInstance() {
        if (INSTANCE == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            INSTANCE = new Singleton03();
        }
        return INSTANCE;
    }
}
