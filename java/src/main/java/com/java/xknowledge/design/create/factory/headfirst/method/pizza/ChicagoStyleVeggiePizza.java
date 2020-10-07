package com.java.xknowledge.design.create.factory.headfirst.method.pizza;

public class ChicagoStyleVeggiePizza extends Pizza {
	public ChicagoStyleVeggiePizza() {
		name = "Chicago Deep Dish Veggie com.java.xknowledge.design.create.factory.headfirst.builder.pizza.Pizza";
		dough = "Extra Thick Crust com.java.xknowledge.design.create.factory.headfirst.builder.dough.Dough";
		sauce = "Plum Tomato com.java.xknowledge.design.create.factory.headfirst.builder.sauce.Sauce";
 
		toppings.add("Shredded Mozzarella com.java.xknowledge.design.create.factory.headfirst.builder.cheese.Cheese");
		toppings.add("Black Olives");
		toppings.add("com.java.xknowledge.design.create.factory.headfirst.builder.veggies.Spinach");
		toppings.add("com.java.xknowledge.design.create.factory.headfirst.builder.veggies.Eggplant");
	}
 
	public void cut() {
		System.out.println("Cutting the pizza into square slices");
	}
}
