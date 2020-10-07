package com.java.xknowledge.design.create.factory.headfirst.method.pizza;

/**
 * 纽约风味的披萨
 */
public class NYStyleCheesePizza extends Pizza {

	public NYStyleCheesePizza() { 
		name = "NY Style com.java.xknowledge.design.create.factory.headfirst.builder.sauce.Sauce and com.java.xknowledge.design.create.factory.headfirst.builder.cheese.Cheese com.java.xknowledge.design.create.factory.headfirst.builder.pizza.Pizza";
		dough = "Thin Crust com.java.xknowledge.design.create.factory.headfirst.builder.dough.Dough";
		sauce = "Marinara com.java.xknowledge.design.create.factory.headfirst.builder.sauce.Sauce";    //纽约风味的披萨使用大蒜番茄和博饼
 
		toppings.add("Grated Reggiano com.java.xknowledge.design.create.factory.headfirst.builder.cheese.Cheese");    //上面覆盖意大利干酪
	}
}
