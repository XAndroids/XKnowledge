package com.java.xknowledge.design.action.strategy.headfirst;

import com.java.xknowledge.design.action.strategy.headfirst.duck.Duck;
import com.java.xknowledge.design.action.strategy.headfirst.duck.MallardDuck;
import com.java.xknowledge.design.action.strategy.headfirst.duck.ModelDuck;
import com.java.xknowledge.design.action.strategy.headfirst.behavior.fly.FlyRocketPowered;

public class MiniDuckSimulator1 {
 
	public static void main(String[] args) {
 
		Duck mallard = new MallardDuck();
		mallard.performQuack();
		mallard.performFly();
   
		Duck model = new ModelDuck();
		model.performFly();
		model.setFlyBehavior(new FlyRocketPowered());
		model.performFly();

	}
}
