package com.java.xknowledge.design.create.factory.headfirst.method.pizza;

import java.util.ArrayList;

public abstract class Pizza {
	String name;    //所有的披萨都有具体的名称、面团类型、酱料类型和一套作料
	String dough;
	String sauce;
	ArrayList toppings = new ArrayList();
 
	public void prepare() {    //抽象类型提供了默写默认的基本做法，子类可以覆盖自定义
		System.out.println("Preparing " + name);    //但是制作流程在工厂中确定
		System.out.println("Tossing dough...");
		System.out.println("Adding sauce...");
		System.out.println("Adding toppings: ");
		for (int i = 0; i < toppings.size(); i++) {
			System.out.println("   " + toppings.get(i));
		}
	}
  
	public void bake() {
		System.out.println("Bake for 25 minutes at 350");
	}
 
	public void cut() {
		System.out.println("Cutting the pizza into diagonal slices");
	}
  
	public void box() {
		System.out.println("Place pizza in official PizzaStore box");
	}
 
	public String getName() {
		return name;
	}

	public String toString() {
		StringBuffer display = new StringBuffer();
		display.append("---- " + name + " ----\n");
		display.append(dough + "\n");
		display.append(sauce + "\n");
		for (int i = 0; i < toppings.size(); i++) {
			display.append((String )toppings.get(i) + "\n");
		}
		return display.toString();
	}
}

 
 
