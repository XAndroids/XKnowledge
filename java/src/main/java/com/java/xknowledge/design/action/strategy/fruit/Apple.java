package com.java.xknowledge.design.action.strategy.fruit;

public class Apple implements Fruit {
    private int price = 200;

    public Apple() {
    }

    @Override
    public int price() {
        return price;
    }

    @Override
    public void draw() {
        System.out.print("苹果红富士\n");
    }

}
