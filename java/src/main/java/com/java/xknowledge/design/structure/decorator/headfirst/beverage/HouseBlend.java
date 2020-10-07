package com.java.xknowledge.design.structure.decorator.headfirst.beverage;

/**
 * 首选咖啡（星巴克咖啡名）
 */
public class HouseBlend extends Beverage {
    public HouseBlend() {
        description = "House Blend Coffee";
    }

    public double cost() {
        return .89;
    }
}

