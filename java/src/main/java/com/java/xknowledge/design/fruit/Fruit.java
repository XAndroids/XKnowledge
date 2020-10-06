package com.java.xknowledge.design.fruit;
import com.java.xknowledge.design.action.visit.SaleVisit;

/**
 * 水果接口
 */
public interface Fruit {
    int price();

    void draw();

    /**
     * 访问者模式，传入accept在Fruit内调用saleVisit.sell(this)访问
     */
    double accept(SaleVisit saleVisit);
}
