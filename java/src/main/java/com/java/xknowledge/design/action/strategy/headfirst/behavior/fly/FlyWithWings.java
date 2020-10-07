package com.java.xknowledge.design.action.strategy.headfirst.behavior.fly;

/**
 * 飞行行为实现，给真会飞的鸭子使用
 */
public class FlyWithWings implements FlyBehavior {
	public void fly() {
		System.out.println("I'm flying!!");
	}
}
