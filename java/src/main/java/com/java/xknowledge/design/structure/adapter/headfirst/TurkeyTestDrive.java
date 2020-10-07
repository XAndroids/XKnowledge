package com.java.xknowledge.design.structure.adapter.headfirst;

import com.java.xknowledge.design.structure.adapter.headfirst.duck.MallardDuck;
import com.java.xknowledge.design.structure.adapter.headfirst.turkey.DuckAdapter;
import com.java.xknowledge.design.structure.adapter.headfirst.turkey.Turkey;

public class TurkeyTestDrive {
    public static void main(String[] args) {
        MallardDuck duck = new MallardDuck();
        Turkey duckAdapter = new DuckAdapter(duck);

        for (int i = 0; i < 10; i++) {
            System.out.println("The DuckAdapter says...");
            duckAdapter.gobble();
            duckAdapter.fly();
        }
    }
}
