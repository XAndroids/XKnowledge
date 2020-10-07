package com.java.xknowledge.design.action.strategy.headfirst;

import com.java.xknowledge.design.action.strategy.headfirst.duck.DecoyDuck;
import com.java.xknowledge.design.action.strategy.headfirst.duck.MallardDuck;
import com.java.xknowledge.design.action.strategy.headfirst.duck.ModelDuck;
import com.java.xknowledge.design.action.strategy.headfirst.duck.RubberDuck;
import com.java.xknowledge.design.action.strategy.headfirst.behavior.fly.FlyRocketPowered;

/**
 * 策略模式
 * 参考《Head First 设计模式》
 */
public class MiniDuckSimulator {
 
	public static void main(String[] args) {
		MallardDuck mallard = new MallardDuck();
		RubberDuck rubberDuckie = new RubberDuck();
		DecoyDuck decoy = new DecoyDuck();
		ModelDuck model = new ModelDuck();

		mallard.performQuack();
		rubberDuckie.performQuack();
		decoy.performQuack();
   
		model.performFly();	
		model.setFlyBehavior(new FlyRocketPowered());
		model.performFly();
	}
}
