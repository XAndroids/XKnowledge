package com.java.xknowledge.design.create.factory.headfirst.method.store;

import com.java.xknowledge.design.create.factory.headfirst.method.pizza.ChicagoStyleCheesePizza;
import com.java.xknowledge.design.create.factory.headfirst.method.pizza.ChicagoStyleClamPizza;
import com.java.xknowledge.design.create.factory.headfirst.method.pizza.ChicagoStylePepperoniPizza;
import com.java.xknowledge.design.create.factory.headfirst.method.pizza.ChicagoStyleVeggiePizza;
import com.java.xknowledge.design.create.factory.headfirst.method.pizza.Pizza;

/**
 * 芝加哥披萨店
 */
public class ChicagoPizzaStore extends PizzaStore {
	//实现createPizza方法，根据传入的类型生成不同风味的芝加哥披萨
	Pizza createPizza(String item) {
        	if (item.equals("cheese")) {
            		return new ChicagoStyleCheesePizza();
        	} else if (item.equals("veggie")) {
        	    	return new ChicagoStyleVeggiePizza();
        	} else if (item.equals("clam")) {
        	    	return new ChicagoStyleClamPizza();
        	} else if (item.equals("pepperoni")) {
            		return new ChicagoStylePepperoniPizza();
        	} else return null;
	}
}
