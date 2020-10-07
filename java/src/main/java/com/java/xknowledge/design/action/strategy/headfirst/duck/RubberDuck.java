package com.java.xknowledge.design.action.strategy.headfirst.duck;

import com.java.xknowledge.design.action.strategy.headfirst.behavior.quack.Squeak;
import com.java.xknowledge.design.action.strategy.headfirst.behavior.fly.FlyNoWay;

public class RubberDuck extends Duck {
 
	public RubberDuck() {
		flyBehavior = new FlyNoWay();
		quackBehavior = new Squeak();
	}
 
	public void display() {
		System.out.println("I'm a rubber duckie");
	}
}
