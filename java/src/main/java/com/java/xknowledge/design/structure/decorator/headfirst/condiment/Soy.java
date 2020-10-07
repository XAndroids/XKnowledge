package com.java.xknowledge.design.structure.decorator.headfirst.condiment;

import com.java.xknowledge.design.structure.decorator.headfirst.beverage.Beverage;

public class Soy extends CondimentDecorator {
	Beverage beverage;

	public Soy(Beverage beverage) {
		this.beverage = beverage;
	}

	public String getDescription() {
		return beverage.getDescription() + ", Soy";
	}

	public double cost() {
		return .15 + beverage.cost();
	}
}
