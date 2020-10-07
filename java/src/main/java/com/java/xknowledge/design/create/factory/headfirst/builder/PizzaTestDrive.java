package com.java.xknowledge.design.create.factory.headfirst.builder;

import com.java.xknowledge.design.create.factory.headfirst.builder.pizza.Pizza;
import com.java.xknowledge.design.create.factory.headfirst.builder.store.ChicagoPizzaStore;
import com.java.xknowledge.design.create.factory.headfirst.builder.store.NYPizzaStore;
import com.java.xknowledge.design.create.factory.headfirst.builder.store.PizzaStore;

/**
 * 抽象工厂模式
 * 参考：《Head First 设计模式》
 */
public class PizzaTestDrive {
 
	public static void main(String[] args) {
		PizzaStore nyStore = new NYPizzaStore();
		PizzaStore chicagoStore = new ChicagoPizzaStore();
 
		Pizza pizza = nyStore.orderPizza("cheese");
		System.out.println("Ethan ordered a " + pizza + "\n");
 
		pizza = chicagoStore.orderPizza("cheese");
		System.out.println("Joel ordered a " + pizza + "\n");

		pizza = nyStore.orderPizza("clam");
		System.out.println("Ethan ordered a " + pizza + "\n");
 
		pizza = chicagoStore.orderPizza("clam");
		System.out.println("Joel ordered a " + pizza + "\n");

		pizza = nyStore.orderPizza("pepperoni");
		System.out.println("Ethan ordered a " + pizza + "\n");
 
		pizza = chicagoStore.orderPizza("pepperoni");
		System.out.println("Joel ordered a " + pizza + "\n");

		pizza = nyStore.orderPizza("veggie");
		System.out.println("Ethan ordered a " + pizza + "\n");
 
		pizza = chicagoStore.orderPizza("veggie");
		System.out.println("Joel ordered a " + pizza + "\n");
	}
}
