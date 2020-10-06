package com.java.xknowledge.design.action.strategy.fruit;

public class Orange implements Fruit {
    private int price = 70;

    public Orange() {
    }

    @Override
    public int price() {
        return price;
    }

    @Override
    public void draw() {
        System.out.print("砂糖桔\n");
    }
}
