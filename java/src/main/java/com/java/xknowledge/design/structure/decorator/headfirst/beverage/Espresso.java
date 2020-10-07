package com.java.xknowledge.design.structure.decorator.headfirst.beverage;

/**
 * 浓缩咖啡饮料，继承Beverage
 */
public class Espresso extends Beverage {

    public Espresso() {
        description = "Espresso";
    }

    public double cost() {
        return 1.99;    //计算价格，不管调料的价格，直接计算返回即可
    }
}
