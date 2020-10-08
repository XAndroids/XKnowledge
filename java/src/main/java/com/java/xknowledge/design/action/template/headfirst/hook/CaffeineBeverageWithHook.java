package com.java.xknowledge.design.action.template.headfirst.hook;

/**
 * 开啡因饮料Hook抽象类
 */
public abstract class CaffeineBeverageWithHook {
 
	public void prepareRecipe() {
		boilWater();
		brew();
		pourInCup();
		if (customerWantsCondiments()) {    //加上一个条件语句，是否成立由customerWantsCondiments决定
			addCondiments();
		}
	}
 
	abstract void brew();
 
	abstract void addCondiments();
 
	void boilWater() {
		System.out.println("Boiling water");
	}
 
	void pourInCup() {
		System.out.println("Pouring into cup");
	}

	//这就是一个"钩子"，子类可以覆盖这个方法，但不一定得这么做
	boolean customerWantsCondiments() {
		return true;
	}    //定义是否成立方法，默认实现返回true
}
