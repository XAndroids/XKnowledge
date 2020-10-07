package com.java.xknowledge.design.action.strategy.headfirst.duck;

import com.java.xknowledge.design.action.strategy.headfirst.behavior.fly.FlyBehavior;
import com.java.xknowledge.design.action.strategy.headfirst.behavior.quack.QuackBehavior;

/**
 * 鸭子接口
 */
public abstract class Duck {
    FlyBehavior flyBehavior;   //鸭子类型包含飞行
    QuackBehavior quackBehavior;    //鸭子类型包含叫行为

    public Duck() {
    }

    public void setFlyBehavior(FlyBehavior fb) {
        flyBehavior = fb;
    }

    public void setQuackBehavior(QuackBehavior qb) {
        quackBehavior = qb;
    }

    abstract void display();    //所有鸭子不同的方法，且每一个都不公用，行为是可以公用故抽象算法簇

    public void performFly() {    //执行飞行委托给具体飞行行为实例执行
        flyBehavior.fly();
    }

    public void performQuack() {    //执行叫委托给具体叫的行为实例执行
        quackBehavior.quack();
    }

    public void swim() {    //所有鸭子共同的拥有方法，不独立成算法簇
        System.out.println("All ducks float, even decoys!");
    }
}
