package com.java.xknowledge.design.fruit;

import com.java.xknowledge.design.action.visit.SaleVisit;

public class Apple implements Fruit {
    private int price = 200;

    @Override
    public int price() {
        return price;
    }

    @Override
    public void draw() {
        System.out.println("苹果红富士");
    }

    @Override
    public double accept(SaleVisit saleVisit) {
        return saleVisit.sell(this);    //this指针可识别真实类型
    }
}
