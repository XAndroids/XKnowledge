package com.java.xknowledge.design.action.strategy.headfirst.behavior.fly;

/**
 * 具有火箭动力飞行行为
 */
public class FlyRocketPowered implements FlyBehavior {
	public void fly() {
		System.out.println("I'm flying with a rocket");
	}
}
