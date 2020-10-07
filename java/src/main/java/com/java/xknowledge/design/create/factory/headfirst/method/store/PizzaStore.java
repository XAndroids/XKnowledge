package com.java.xknowledge.design.create.factory.headfirst.method.store;

import com.java.xknowledge.design.create.factory.headfirst.method.pizza.Pizza;

/**
 * 披萨店抽象类
 */
public abstract class PizzaStore {

    abstract Pizza createPizza(String item);    //抽象方法，在子类中处理对象的实例化

    public Pizza orderPizza(String type) {
        Pizza pizza = createPizza(type);    //此方法就犹如一个工厂
        System.out.println("--- Making a " + pizza.getName() + " ---");
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }
}
