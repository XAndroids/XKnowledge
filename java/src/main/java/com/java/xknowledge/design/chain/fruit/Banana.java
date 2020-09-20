package com.java.xknowledge.design.chain.fruit;

public class Banana implements Fruit {
    private int price = 60;

    public Banana() {
    }

    @Override
    public int price() {
        return price;
    }

    @Override
    public void draw() {
        System.out.print("仙人蕉\n");
    }
}
