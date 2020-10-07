package com.java.xknowledge.design.action.strategy.headfirst.behavior.quack;

/**
 * 鸭子叫具体实现类
 */
public class Quack implements QuackBehavior {
	public void quack() {
		System.out.println("Quack");
	}
}
