package com.java.xknowledge.design.singleton;

/**
 * 静态内部类方式
 * JVM保证线程安全
 * 加载外部类时不会加载内部类，可以实现懒加载
 */
public class Singleton05 {
    private Singleton05() {

    }

    static class SingletonHolder {
        private static final Singleton05 INSTANCE = new Singleton05();
    }

    public static Singleton05 getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
