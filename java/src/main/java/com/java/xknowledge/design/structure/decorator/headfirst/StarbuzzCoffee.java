package com.java.xknowledge.design.structure.decorator.headfirst;

import com.java.xknowledge.design.structure.decorator.headfirst.beverage.Beverage;
import com.java.xknowledge.design.structure.decorator.headfirst.beverage.DarkRoast;
import com.java.xknowledge.design.structure.decorator.headfirst.beverage.Espresso;
import com.java.xknowledge.design.structure.decorator.headfirst.beverage.HouseBlend;
import com.java.xknowledge.design.structure.decorator.headfirst.condiment.Mocha;
import com.java.xknowledge.design.structure.decorator.headfirst.condiment.Soy;
import com.java.xknowledge.design.structure.decorator.headfirst.condiment.Whip;

/**
 * 星巴克咖啡
 */
public class StarbuzzCoffee {

    public static void main(String args[]) {
        Beverage beverage = new Espresso();    //订一杯浓缩咖啡，不需要调料
        System.out.println(beverage.getDescription()
                + " $" + beverage.cost());

        Beverage beverage2 = new DarkRoast();    //订一杯DarkRoast，添加两份Mocha和一份Whip
        beverage2 = new Mocha(beverage2);
        beverage2 = new Mocha(beverage2);
        beverage2 = new Whip(beverage2);
        System.out.println(beverage2.getDescription()
                + " $" + beverage2.cost());

        Beverage beverage3 = new HouseBlend();    //订一杯HouseBlend，添加一份Soy、Mocha和Whip
        beverage3 = new Soy(beverage3);
        beverage3 = new Mocha(beverage3);
        beverage3 = new Whip(beverage3);
        System.out.println(beverage3.getDescription()
                + " $" + beverage3.cost());
    }
}
