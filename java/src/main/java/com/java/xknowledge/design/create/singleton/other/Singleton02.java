package com.java.xknowledge.design.create.singleton.other;

/**
 * 懒汉式
 * 达到了按需加载的目的，但是线程不安全
 */
public class Singleton02 {
    private static Singleton02 INSTANCE;

    private Singleton02() {

    }

    public static Singleton02 getInstance() {
        if (INSTANCE == null) {
            //模拟线程安全
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            INSTANCE = new Singleton02();
        }
        return INSTANCE;
    }
}
