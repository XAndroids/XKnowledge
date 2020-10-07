package com.java.xknowledge.design.create.factory.headfirst.method.pizza;

public class NYStyleClamPizza extends Pizza {

	public NYStyleClamPizza() {
		name = "NY Style Clam com.java.xknowledge.design.create.factory.headfirst.builder.pizza.Pizza";
		dough = "Thin Crust com.java.xknowledge.design.create.factory.headfirst.builder.dough.Dough";
		sauce = "Marinara com.java.xknowledge.design.create.factory.headfirst.builder.sauce.Sauce";
 
		toppings.add("Grated Reggiano com.java.xknowledge.design.create.factory.headfirst.builder.cheese.Cheese");
		toppings.add("Fresh com.java.xknowledge.design.create.factory.headfirst.builder.clams.Clams from Long Island Sound");
	}
}
