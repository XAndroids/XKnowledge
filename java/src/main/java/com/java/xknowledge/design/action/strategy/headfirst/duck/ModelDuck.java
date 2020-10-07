package com.java.xknowledge.design.action.strategy.headfirst.duck;

import com.java.xknowledge.design.action.strategy.headfirst.behavior.quack.Quack;
import com.java.xknowledge.design.action.strategy.headfirst.behavior.fly.FlyNoWay;

/**
 * 模型鸭子具体实现
 */
public class ModelDuck extends Duck {
	public ModelDuck() {
		flyBehavior = new FlyNoWay();    //它不能飞行
		quackBehavior = new Quack();    //但是它可以叫
	}

	public void display() {
		System.out.println("I'm a model duck");
	}
}
