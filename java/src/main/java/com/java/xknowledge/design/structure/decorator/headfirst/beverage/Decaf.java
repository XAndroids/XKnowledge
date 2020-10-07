package com.java.xknowledge.design.structure.decorator.headfirst.beverage;

import com.java.xknowledge.design.structure.decorator.headfirst.beverage.Beverage;

public class Decaf extends Beverage {
	public Decaf() {
		description = "Decaf Coffee";
	}
 
	public double cost() {
		return 1.05;
	}
}

