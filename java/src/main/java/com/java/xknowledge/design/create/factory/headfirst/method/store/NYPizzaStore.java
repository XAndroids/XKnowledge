package com.java.xknowledge.design.create.factory.headfirst.method.store;

import com.java.xknowledge.design.create.factory.headfirst.method.pizza.NYStyleCheesePizza;
import com.java.xknowledge.design.create.factory.headfirst.method.pizza.NYStyleClamPizza;
import com.java.xknowledge.design.create.factory.headfirst.method.pizza.NYStylePepperoniPizza;
import com.java.xknowledge.design.create.factory.headfirst.method.pizza.NYStyleVeggiePizza;
import com.java.xknowledge.design.create.factory.headfirst.method.pizza.Pizza;

/**
 * 纽约披萨店
 */
public class NYPizzaStore extends PizzaStore {

    //实现createPizza方法，根据传入的类型生成不同风味的纽约披萨
    Pizza createPizza(String item) {
        if (item.equals("cheese")) {
            return new NYStyleCheesePizza();
        } else if (item.equals("veggie")) {
            return new NYStyleVeggiePizza();
        } else if (item.equals("clam")) {
            return new NYStyleClamPizza();
        } else if (item.equals("pepperoni")) {
            return new NYStylePepperoniPizza();
        } else return null;
    }
}
