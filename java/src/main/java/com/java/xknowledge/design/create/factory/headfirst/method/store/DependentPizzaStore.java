package com.java.xknowledge.design.create.factory.headfirst.method.store;

import com.java.xknowledge.design.create.factory.headfirst.method.pizza.NYStyleCheesePizza;
import com.java.xknowledge.design.create.factory.headfirst.method.pizza.NYStyleClamPizza;
import com.java.xknowledge.design.create.factory.headfirst.method.pizza.NYStylePepperoniPizza;
import com.java.xknowledge.design.create.factory.headfirst.method.pizza.NYStyleVeggiePizza;
import com.java.xknowledge.design.create.factory.headfirst.method.pizza.Pizza;
import com.java.xknowledge.design.create.factory.headfirst.method.pizza.ChicagoStyleCheesePizza;
import com.java.xknowledge.design.create.factory.headfirst.method.pizza.ChicagoStyleClamPizza;
import com.java.xknowledge.design.create.factory.headfirst.method.pizza.ChicagoStylePepperoniPizza;
import com.java.xknowledge.design.create.factory.headfirst.method.pizza.ChicagoStyleVeggiePizza;

public class DependentPizzaStore {
 
	public Pizza createPizza(String style, String type) {
		Pizza pizza = null;
		if (style.equals("NY")) {
			if (type.equals("cheese")) {
				pizza = new NYStyleCheesePizza();
			} else if (type.equals("veggie")) {
				pizza = new NYStyleVeggiePizza();
			} else if (type.equals("clam")) {
				pizza = new NYStyleClamPizza();
			} else if (type.equals("pepperoni")) {
				pizza = new NYStylePepperoniPizza();
			}
		} else if (style.equals("Chicago")) {
			if (type.equals("cheese")) {
				pizza = new ChicagoStyleCheesePizza();
			} else if (type.equals("veggie")) {
				pizza = new ChicagoStyleVeggiePizza();
			} else if (type.equals("clam")) {
				pizza = new ChicagoStyleClamPizza();
			} else if (type.equals("pepperoni")) {
				pizza = new ChicagoStylePepperoniPizza();
			}
		} else {
			System.out.println("Error: invalid type of pizza");
			return null;
		}
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		return pizza;
	}
}
