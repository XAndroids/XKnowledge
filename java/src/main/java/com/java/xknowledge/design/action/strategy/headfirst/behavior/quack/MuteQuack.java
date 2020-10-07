package com.java.xknowledge.design.action.strategy.headfirst.behavior.quack;

public class MuteQuack implements QuackBehavior {
	public void quack() {
		System.out.println("<< Silence >>");
	}
}
