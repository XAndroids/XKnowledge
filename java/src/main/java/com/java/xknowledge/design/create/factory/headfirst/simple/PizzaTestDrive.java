package com.java.xknowledge.design.create.factory.headfirst.simple;

import com.java.xknowledge.design.create.factory.headfirst.simple.pizza.Pizza;

public class PizzaTestDrive {

    public static void main(String[] args) {
        //创建披萨店（包含披萨工厂对象）
        SimplePizzaFactory factory = new SimplePizzaFactory();
        PizzaStore store = new PizzaStore(factory);

        //预订cheese披萨
        Pizza pizza = store.orderPizza("cheese");
        System.out.println("We ordered a " + pizza.getName() + "\n");

        //预订veggie披萨
        pizza = store.orderPizza("veggie");
        System.out.println("We ordered a " + pizza.getName() + "\n");
    }
}
