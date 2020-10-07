package com.java.xknowledge.design.create.factory.headfirst.method.pizza;

public class ChicagoStyleClamPizza extends Pizza {
	public ChicagoStyleClamPizza() {
		name = "Chicago Style Clam com.java.xknowledge.design.create.factory.headfirst.builder.pizza.Pizza";
		dough = "Extra Thick Crust com.java.xknowledge.design.create.factory.headfirst.builder.dough.Dough";
		sauce = "Plum Tomato com.java.xknowledge.design.create.factory.headfirst.builder.sauce.Sauce";
 
		toppings.add("Shredded Mozzarella com.java.xknowledge.design.create.factory.headfirst.builder.cheese.Cheese");
		toppings.add("Frozen com.java.xknowledge.design.create.factory.headfirst.builder.clams.Clams from Chesapeake Bay");
	}
 
	public void cut() {
		System.out.println("Cutting the pizza into square slices");
	}
}
