package com.java.xknowledge.design.create.factory.headfirst.method.pizza;

public class NYStylePepperoniPizza extends Pizza {

	public NYStylePepperoniPizza() {
		name = "NY Style com.java.xknowledge.design.create.factory.headfirst.builder.pepperoni.Pepperoni com.java.xknowledge.design.create.factory.headfirst.builder.pizza.Pizza";
		dough = "Thin Crust com.java.xknowledge.design.create.factory.headfirst.builder.dough.Dough";
		sauce = "Marinara com.java.xknowledge.design.create.factory.headfirst.builder.sauce.Sauce";
 
		toppings.add("Grated Reggiano com.java.xknowledge.design.create.factory.headfirst.builder.cheese.Cheese");
		toppings.add("Sliced com.java.xknowledge.design.create.factory.headfirst.builder.pepperoni.Pepperoni");
		toppings.add("com.java.xknowledge.design.create.factory.headfirst.builder.veggies.Garlic");
		toppings.add("com.java.xknowledge.design.create.factory.headfirst.builder.veggies.Onion");
		toppings.add("Mushrooms");
		toppings.add("Red Pepper");
	}
}
