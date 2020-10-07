package com.java.xknowledge.design.create.singleton.headfirst.threadsafe;

/**
 * 方法锁
 */
public class Singleton {
	private static Singleton uniqueInstance;

	private Singleton() {}

	//方法锁保证线程安全
	public static synchronized Singleton getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new Singleton();
		}
		return uniqueInstance;
	}
}
