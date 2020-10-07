package com.java.xknowledge.design.create.factory.headfirst.method.pizza;

public class NYStyleVeggiePizza extends Pizza {

	public NYStyleVeggiePizza() {
		name = "NY Style Veggie com.java.xknowledge.design.create.factory.headfirst.builder.pizza.Pizza";
		dough = "Thin Crust com.java.xknowledge.design.create.factory.headfirst.builder.dough.Dough";
		sauce = "Marinara com.java.xknowledge.design.create.factory.headfirst.builder.sauce.Sauce";
 
		toppings.add("Grated Reggiano com.java.xknowledge.design.create.factory.headfirst.builder.cheese.Cheese");
		toppings.add("com.java.xknowledge.design.create.factory.headfirst.builder.veggies.Garlic");
		toppings.add("com.java.xknowledge.design.create.factory.headfirst.builder.veggies.Onion");
		toppings.add("Mushrooms");
		toppings.add("Red Pepper");
	}
}
