package com.java.xknowledge.design.create.factory.headfirst.builder.factory;

import com.java.xknowledge.design.create.factory.headfirst.builder.veggies.BlackOlives;
import com.java.xknowledge.design.create.factory.headfirst.builder.cheese.Cheese;
import com.java.xknowledge.design.create.factory.headfirst.builder.clams.Clams;
import com.java.xknowledge.design.create.factory.headfirst.builder.dough.Dough;
import com.java.xknowledge.design.create.factory.headfirst.builder.veggies.Eggplant;
import com.java.xknowledge.design.create.factory.headfirst.builder.clams.FrozenClams;
import com.java.xknowledge.design.create.factory.headfirst.builder.cheese.MozzarellaCheese;
import com.java.xknowledge.design.create.factory.headfirst.builder.pepperoni.Pepperoni;
import com.java.xknowledge.design.create.factory.headfirst.builder.sauce.PlumTomatoSauce;
import com.java.xknowledge.design.create.factory.headfirst.builder.sauce.Sauce;
import com.java.xknowledge.design.create.factory.headfirst.builder.pepperoni.SlicedPepperoni;
import com.java.xknowledge.design.create.factory.headfirst.builder.veggies.Spinach;
import com.java.xknowledge.design.create.factory.headfirst.builder.dough.ThickCrustDough;
import com.java.xknowledge.design.create.factory.headfirst.builder.veggies.Veggies;

/**
 * 芝加哥原料工厂
 */
public class ChicagoPizzaIngredientFactory implements PizzaIngredientFactory {

    public Dough createDough() {
        return new ThickCrustDough();
    }

    public Sauce createSauce() {
        return new PlumTomatoSauce();
    }

    public Cheese createCheese() {
        return new MozzarellaCheese();
    }

    public Veggies[] createVeggies() {
        Veggies veggies[] = {new BlackOlives(),
                new Spinach(),
                new Eggplant()};
        return veggies;
    }

    public Pepperoni createPepperoni() {
        return new SlicedPepperoni();
    }

    public Clams createClam() {
        return new FrozenClams();
    }
}
