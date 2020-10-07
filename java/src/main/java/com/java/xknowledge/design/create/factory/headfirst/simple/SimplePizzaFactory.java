package com.java.xknowledge.design.create.factory.headfirst.simple;

import com.java.xknowledge.design.create.factory.headfirst.simple.pizza.CheesePizza;
import com.java.xknowledge.design.create.factory.headfirst.simple.pizza.ClamPizza;
import com.java.xknowledge.design.create.factory.headfirst.simple.pizza.PepperoniPizza;
import com.java.xknowledge.design.create.factory.headfirst.simple.pizza.Pizza;
import com.java.xknowledge.design.create.factory.headfirst.simple.pizza.VeggiePizza;

/**
 * 简单工厂：根据传入的类型创建同一个接口类型不同的对象
 */
public class SimplePizzaFactory {

    public Pizza createPizza(String type) {
        Pizza pizza = null;

        if (type.equals("cheese")) {
            pizza = new CheesePizza();
        } else if (type.equals("pepperoni")) {
            pizza = new PepperoniPizza();
        } else if (type.equals("clam")) {
            pizza = new ClamPizza();
        } else if (type.equals("veggie")) {
            pizza = new VeggiePizza();
        }
        return pizza;
    }
}
