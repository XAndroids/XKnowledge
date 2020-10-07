package com.java.xknowledge.design.structure.decorator.headfirst.condiment;

import com.java.xknowledge.design.structure.decorator.headfirst.beverage.Beverage;

/**
 * 调料装饰类
 */
public abstract class CondimentDecorator extends Beverage {    //首先，必须让CondimentDecorator能替代Beverage故继承Beverage
	public abstract String getDescription();
}
