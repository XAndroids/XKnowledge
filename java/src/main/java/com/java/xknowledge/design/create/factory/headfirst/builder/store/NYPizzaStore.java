package com.java.xknowledge.design.create.factory.headfirst.builder.store;

import com.java.xknowledge.design.create.factory.headfirst.builder.pizza.PepperoniPizza;
import com.java.xknowledge.design.create.factory.headfirst.builder.pizza.VeggiePizza;
import com.java.xknowledge.design.create.factory.headfirst.builder.factory.NYPizzaIngredientFactory;
import com.java.xknowledge.design.create.factory.headfirst.builder.factory.PizzaIngredientFactory;
import com.java.xknowledge.design.create.factory.headfirst.builder.pizza.CheesePizza;
import com.java.xknowledge.design.create.factory.headfirst.builder.pizza.ClamPizza;
import com.java.xknowledge.design.create.factory.headfirst.builder.pizza.Pizza;

public class NYPizzaStore extends PizzaStore {
 
	protected Pizza createPizza(String item) {
		Pizza pizza = null;
		PizzaIngredientFactory ingredientFactory = new NYPizzaIngredientFactory();
 
		if (item.equals("cheese")) {
  
			pizza = new CheesePizza(ingredientFactory);
			pizza.setName("New York Style com.java.xknowledge.design.create.factory.headfirst.builder.cheese.Cheese com.java.xknowledge.design.create.factory.headfirst.builder.pizza.Pizza");
  
		} else if (item.equals("veggie")) {
 
			pizza = new VeggiePizza(ingredientFactory);
			pizza.setName("New York Style Veggie com.java.xknowledge.design.create.factory.headfirst.builder.pizza.Pizza");
 
		} else if (item.equals("clam")) {
 
			pizza = new ClamPizza(ingredientFactory);
			pizza.setName("New York Style Clam com.java.xknowledge.design.create.factory.headfirst.builder.pizza.Pizza");
 
		} else if (item.equals("pepperoni")) {

			pizza = new PepperoniPizza(ingredientFactory);
			pizza.setName("New York Style com.java.xknowledge.design.create.factory.headfirst.builder.pepperoni.Pepperoni com.java.xknowledge.design.create.factory.headfirst.builder.pizza.Pizza");
 
		} 
		return pizza;
	}
}
