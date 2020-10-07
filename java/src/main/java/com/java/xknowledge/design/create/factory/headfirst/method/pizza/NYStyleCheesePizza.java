package com.java.xknowledge.design.create.factory.headfirst.method.pizza;

/**
 * 纽约风味的披萨
 */
public class NYStyleCheesePizza extends Pizza {

	public NYStyleCheesePizza() { 
		name = "NY Style Sauce and Cheese Pizza";
		dough = "Thin Crust Dough";
		sauce = "Marinara Sauce";    //纽约风味的披萨使用大蒜番茄和博饼
 
		toppings.add("Grated Reggiano Cheese");    //上面覆盖意大利干酪
	}
}
