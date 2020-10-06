package com.java.xknowledge.design.action.visit;

import com.java.xknowledge.design.common.fruit.Apple;
import com.java.xknowledge.design.common.fruit.Fruit;
import com.java.xknowledge.design.common.fruit.Orange;

/**
 * 促销访问者，用于打折促销时，重新计算活动后的价格
 */
public class SaleVisit {
    /**
     * 苹果5折销售
     */
    public double sell(Apple apple) {
        System.out.println("apple's price: " + apple.price());
        double salePrice = apple.price() * 0.5;
        System.out.println("apple's salePrice: " + salePrice);
        return salePrice;
    }

    /**
     * 橘子满100减10元
     */
    public double sell(Orange orange) {
        System.out.println("orange's price: " + orange.price());
        if (orange.price() > 100) {
            double salePrice = orange.price() - 10;
            System.out.println("orange's salePrice: " + salePrice);
            return salePrice;
        } else {
            return orange.price();
        }
    }

    /**
     * 其它水果去打折
     */
    public double sell(Fruit fruit) {
        System.out.println("fruit's price: " + fruit.price());
        return fruit.price();
    }
}
