package com.java.xknowledge.design.action.strategy.headfirst.duck;

import com.java.xknowledge.design.action.strategy.headfirst.behavior.fly.FlyWithWings;
import com.java.xknowledge.design.action.strategy.headfirst.behavior.quack.Quack;

public class RedHeadDuck extends Duck {
 
	public RedHeadDuck() {
		flyBehavior = new FlyWithWings();
		quackBehavior = new Quack();
	}
 
	public void display() {
		System.out.println("I'm a real Red Headed duck");
	}
}
