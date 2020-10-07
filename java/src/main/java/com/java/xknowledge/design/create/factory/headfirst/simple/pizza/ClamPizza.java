package com.java.xknowledge.design.create.factory.headfirst.simple.pizza;

public class ClamPizza extends Pizza {
	public ClamPizza() {
		name = "Clam com.java.xknowledge.design.create.factory.headfirst.builder.pizza.Pizza";
		dough = "Thin crust";
		sauce = "White garlic sauce";
		toppings.add("com.java.xknowledge.design.create.factory.headfirst.builder.clams.Clams");
		toppings.add("Grated parmesan cheese");
	}
}
