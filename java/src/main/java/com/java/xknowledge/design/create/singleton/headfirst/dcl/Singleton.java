package com.java.xknowledge.design.create.singleton.headfirst.dcl;

//
// Danger!  This implementation of Singleton not
// guaranteed to work prior to Java 5
//
/**
 * 双重锁检查
 */
public class Singleton {
	private volatile static Singleton uniqueInstance;    //volatile抑制指令重排，避免空判断失效
 
	private Singleton() {}
 
	public static Singleton getInstance() {
		if (uniqueInstance == null) {    //该检查避免，锁同步的消耗
			synchronized (Singleton.class) {
				if (uniqueInstance == null) {
					uniqueInstance = new Singleton();
				}
			}
		}
		return uniqueInstance;
	}
}
