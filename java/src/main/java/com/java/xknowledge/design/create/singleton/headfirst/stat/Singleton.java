package com.java.xknowledge.design.create.singleton.headfirst.stat;

/**
 * 饿汉式
 */
public class Singleton {
	private static Singleton uniqueInstance = new Singleton();    //在静态初始化时创建单例，保证了线程安全
 
	private Singleton() {}
 
	public static Singleton getInstance() {
		return uniqueInstance;    //已经有实例了直接返回
	}
}
