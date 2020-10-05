package com.java.xknowledge.design.action.visit.fruit;

import com.java.xknowledge.design.action.visit.SaleVisit;

public class Orange implements Fruit {
    private int price = 100;

    @Override
    public int price() {
        return price;
    }

    @Override
    public void draw() {
        System.out.print("橘子");
    }

    @Override
    public double accept(SaleVisit saleVisit) {
        return saleVisit.sell(this);    //由于saleVisit.sell()无法识别fruit真实类型，故在fruit对象内部调用
    }
}
