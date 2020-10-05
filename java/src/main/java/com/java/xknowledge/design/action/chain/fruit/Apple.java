package com.java.xknowledge.design.action.chain.fruit;

public class Apple implements Fruit {
    private int price = 100;

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
