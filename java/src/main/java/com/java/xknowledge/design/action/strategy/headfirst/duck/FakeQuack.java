package com.java.xknowledge.design.action.strategy.headfirst.duck;

import com.java.xknowledge.design.action.strategy.headfirst.behavior.quack.QuackBehavior;

public class FakeQuack implements QuackBehavior {
	public void quack() {
		System.out.println("Qwak");
	}
}
