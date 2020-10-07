package com.java.xknowledge.design.create.factory.headfirst.simple.pizza;

import java.util.ArrayList;

/**
 * 披萨类型抽象类
 */
abstract public class Pizza {
	String name;    //定义披萨的一些属性
	String dough;
	String sauce;
	ArrayList toppings = new ArrayList();

	public String getName() {
		return name;
	}

	//定义披萨制作的不同阶段流程
	//准备阶段
	public void prepare() {
		System.out.println("Preparing " + name);
	}

	//烘焙阶段
	public void bake() {
		System.out.println("Baking " + name);
	}

	//切块阶段
	public void cut() {
		System.out.println("Cutting " + name);
	}

	//打包阶段
	public void box() {
		System.out.println("Boxing " + name);
	}

	public String toString() {
		// code to display pizza name and ingredients
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

