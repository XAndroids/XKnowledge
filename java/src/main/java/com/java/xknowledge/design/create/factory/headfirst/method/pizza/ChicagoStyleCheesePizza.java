package com.java.xknowledge.design.create.factory.headfirst.method.pizza;

/**
 * 芝加哥风味的披萨
 */
public class ChicagoStyleCheesePizza extends Pizza {

	public ChicagoStyleCheesePizza() { 
		name = "Chicago Style Deep Dish Cheese Pizza";
		dough = "Extra Thick Crust Dough";
		sauce = "Plum Tomato Sauce";    //芝加哥披萨使用小番茄作为酱料，并使用厚饼
 
		toppings.add("Shredded Mozzarella Cheese");    //芝加哥风味的深盘披萨使用许多mozzazella(意大利白甘露)
	}
 
	public void cut() {
		System.out.println("Cutting the pizza into square slices");    //芝加哥风味披萨覆盖了cut方法，将披萨切为方形
	}
}
