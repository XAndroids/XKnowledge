package com.java.xknowledge.design.create.factory.headfirst.simple.pizza;

public class PepperoniPizza extends Pizza {
	public PepperoniPizza() {
		name = "com.java.xknowledge.design.create.factory.headfirst.builder.pepperoni.Pepperoni com.java.xknowledge.design.create.factory.headfirst.builder.pizza.Pizza";
		dough = "Crust";
		sauce = "Marinara sauce";
		toppings.add("Sliced com.java.xknowledge.design.create.factory.headfirst.builder.pepperoni.Pepperoni");
		toppings.add("Sliced com.java.xknowledge.design.create.factory.headfirst.builder.veggies.Onion");
		toppings.add("Grated parmesan cheese");
	}
}
