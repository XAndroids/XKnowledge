package com.java.xknowledge.design.action.strategy.headfirst.behavior.fly;

/**
 * 飞行行为实现，给不会飞的鸭子，橡皮鸭等使用
 */
public class FlyNoWay implements FlyBehavior {
	public void fly() {
		System.out.println("I can't fly");
	}
}
