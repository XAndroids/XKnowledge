package com.java.xknowledge.design.create.singleton.other;

/**
 * 饿汉式:
 * 类加载到内存后，就实例化一个单例，JVM保证线程安全
 * 有点：简单实用，推荐使用！
 * 缺点：不管用到与否，类装载时就完成实例化
 */
public class Singleton01 {
    private static final Singleton01 INSTANCE = new Singleton01();

    private Singleton01() {

    }

    public static Singleton01 getInstance() {
        return INSTANCE;
    }
}
