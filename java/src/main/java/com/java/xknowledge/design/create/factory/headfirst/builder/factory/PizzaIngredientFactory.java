package com.java.xknowledge.design.create.factory.headfirst.builder.factory;

import com.java.xknowledge.design.create.factory.headfirst.builder.cheese.Cheese;
import com.java.xknowledge.design.create.factory.headfirst.builder.clams.Clams;
import com.java.xknowledge.design.create.factory.headfirst.builder.dough.Dough;
import com.java.xknowledge.design.create.factory.headfirst.builder.pepperoni.Pepperoni;
import com.java.xknowledge.design.create.factory.headfirst.builder.sauce.Sauce;
import com.java.xknowledge.design.create.factory.headfirst.builder.veggies.Veggies;

/**
 * Pizza原料工厂，每一个原料都有一个对应的方法创建该原料
 * 如果每个工厂实例有通用的机制需要实现，可以声明为抽象类
 */
public interface PizzaIngredientFactory {
    Dough createDough();    //创建面团

    Sauce createSauce();    //创建调味汁

    Cheese createCheese();    //创建奶酪

    Veggies[] createVeggies();    //创建蔬菜

    Pepperoni createPepperoni();    //创建香肠

    Clams createClam();    //...
}
