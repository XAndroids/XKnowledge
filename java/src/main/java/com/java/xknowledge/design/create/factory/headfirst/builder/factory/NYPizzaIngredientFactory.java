package com.java.xknowledge.design.create.factory.headfirst.builder.factory;

import com.java.xknowledge.design.create.factory.headfirst.builder.cheese.Cheese;
import com.java.xknowledge.design.create.factory.headfirst.builder.clams.Clams;
import com.java.xknowledge.design.create.factory.headfirst.builder.dough.Dough;
import com.java.xknowledge.design.create.factory.headfirst.builder.clams.FreshClams;
import com.java.xknowledge.design.create.factory.headfirst.builder.veggies.Garlic;
import com.java.xknowledge.design.create.factory.headfirst.builder.sauce.MarinaraSauce;
import com.java.xknowledge.design.create.factory.headfirst.builder.veggies.Mushroom;
import com.java.xknowledge.design.create.factory.headfirst.builder.veggies.Onion;
import com.java.xknowledge.design.create.factory.headfirst.builder.pepperoni.Pepperoni;
import com.java.xknowledge.design.create.factory.headfirst.builder.veggies.RedPepper;
import com.java.xknowledge.design.create.factory.headfirst.builder.cheese.ReggianoCheese;
import com.java.xknowledge.design.create.factory.headfirst.builder.sauce.Sauce;
import com.java.xknowledge.design.create.factory.headfirst.builder.pepperoni.SlicedPepperoni;
import com.java.xknowledge.design.create.factory.headfirst.builder.dough.ThinCrustDough;
import com.java.xknowledge.design.create.factory.headfirst.builder.veggies.Veggies;

/**
 * 纽约原料工厂，对每一种原料，都提供了纽约版本
 */
public class NYPizzaIngredientFactory implements PizzaIngredientFactory {
 
	public Dough createDough() {
		return new ThinCrustDough();
	}
 
	public Sauce createSauce() {
		return new MarinaraSauce();
	}
 
	public Cheese createCheese() {
		return new ReggianoCheese();
	}
 
	public Veggies[] createVeggies() {
		Veggies veggies[] = { new Garlic(), new Onion(), new Mushroom(), new RedPepper() };
		return veggies;
	}
 
	public Pepperoni createPepperoni() {
		return new SlicedPepperoni();
	}

	public Clams createClam() {
		return new FreshClams();
	}
}
