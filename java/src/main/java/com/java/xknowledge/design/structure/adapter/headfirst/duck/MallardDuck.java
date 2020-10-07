package com.java.xknowledge.design.structure.adapter.headfirst.duck;

/**
 * 绿头鸭是鸭子的子类
 */
public class MallardDuck implements Duck {
    public void quack() {
        System.out.println("Quack");    //简单打印鸭子在做什么
    }

    public void fly() {
        System.out.println("I'm flying");
    }
}
