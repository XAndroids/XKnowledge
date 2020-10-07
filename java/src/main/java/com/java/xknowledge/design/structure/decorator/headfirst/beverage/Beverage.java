package com.java.xknowledge.design.structure.decorator.headfirst.beverage;

/**
 * 饮料抽象类
 */
public abstract class Beverage {
	String description = "Unknown Beverage";
  
	public String getDescription() {
		return description;
	}
 
	public abstract double cost();
}
