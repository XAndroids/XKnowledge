package com.java.xknowledge.design.create.factory.headfirst.simple;

import com.java.xknowledge.design.create.factory.headfirst.simple.pizza.Pizza;

/**
 * 披萨店类
 */
public class PizzaStore {
    SimplePizzaFactory factory;    //简单披萨工厂，生产不同的披萨对象

    public PizzaStore(SimplePizzaFactory factory) {
        this.factory = factory;
    }

    public Pizza orderPizza(String type) {
        Pizza pizza;

        pizza = factory.createPizza(type);

        pizza.prepare();    //不同的披萨对象，执行相同的生产流程
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }

}
