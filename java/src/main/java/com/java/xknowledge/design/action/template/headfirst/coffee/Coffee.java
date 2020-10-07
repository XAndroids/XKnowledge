package com.java.xknowledge.design.action.template.headfirst.coffee;

/**
 * 咖啡具体实现类，实现brew和addCondiments两个步骤
 */
public class Coffee extends CaffeineBeverage {
	public void brew() {
		System.out.println("Dripping Coffee through filter");
	}
	public void addCondiments() {
		System.out.println("Adding Sugar and Milk");
	}
}
