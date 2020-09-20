package com.java.xknowledge.design.chain.fruit;

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
