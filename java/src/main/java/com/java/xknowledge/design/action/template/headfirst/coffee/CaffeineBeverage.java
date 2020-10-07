package com.java.xknowledge.design.action.template.headfirst.coffee;

/**
 * 咖啡因饮料抽象类
 */
public abstract class CaffeineBeverage {
  
	public final void prepareRecipe() {    //处理茶和咖啡，声明为final，不希望资料覆盖
		boilWater();
		brew();
		pourInCup();
		addCondiments();
	}
 
	abstract void brew();    //因为咖啡和查这两个步骤不同，brew()和addCondiments()抽象留给子类去实现
  
	abstract void addCondiments();
 
	void boilWater() {    //boilWater和pourInCup相同，则具体实现
		System.out.println("Boiling water");
	}
  
	void pourInCup() {
		System.out.println("Pouring into cup");
	}
}
