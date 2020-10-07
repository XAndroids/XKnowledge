package com.java.xknowledge.design.action.strategy.headfirst.duck;

import com.java.xknowledge.design.action.strategy.headfirst.behavior.quack.Quack;
import com.java.xknowledge.design.action.strategy.headfirst.behavior.fly.FlyWithWings;

public class MallardDuck extends Duck {

    public MallardDuck() {
        quackBehavior = new Quack();
        flyBehavior = new FlyWithWings();
    }

    public void display() {
        System.out.println("I'm a real Mallard duck");
    }
}
