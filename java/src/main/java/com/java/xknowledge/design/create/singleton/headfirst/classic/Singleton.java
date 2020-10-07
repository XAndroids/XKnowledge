package com.java.xknowledge.design.create.singleton.headfirst.classic;

/**
 * 懒汉式
 * 注意：这个线程不安全！
 */
public class Singleton {
    private static Singleton uniqueInstance;    //利用一个静态变量来记录Singleton类的唯一实例

    private Singleton() {
    }    //把构造声明为私有方法，只有自己的Singleton类内才可以调用构造函数

    public static Singleton getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Singleton();    //实例化，并返回这个实例
        }
        return uniqueInstance;
    }
}
