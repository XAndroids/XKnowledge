package com.java.xknowledge.design.structure.decorator.headfirst.condiment;

import com.java.xknowledge.design.structure.decorator.headfirst.beverage.Beverage;

/**
 * 摩卡调味料，一个装饰着
 */
public class Mocha extends CondimentDecorator {    //实现调料装饰着接口
    Beverage beverage;    //引用一个饮料实例，作为被装饰着

    public Mocha(Beverage beverage) {    //通过构造器将被装饰着，添加实例中
        this.beverage = beverage;
    }

    public String getDescription() {
        return beverage.getDescription() + ", Mocha";    //完整描述饮料+摩卡调料
    }

    public double cost() {
        return .20 + beverage.cost();    //计算饮料+摩卡调料的价格
    }
}
