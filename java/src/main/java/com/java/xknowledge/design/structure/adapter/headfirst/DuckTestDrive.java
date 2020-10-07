package com.java.xknowledge.design.structure.adapter.headfirst;

import com.java.xknowledge.design.structure.adapter.headfirst.duck.Duck;
import com.java.xknowledge.design.structure.adapter.headfirst.duck.MallardDuck;
import com.java.xknowledge.design.structure.adapter.headfirst.duck.TurkeyAdapter;
import com.java.xknowledge.design.structure.adapter.headfirst.turkey.WildTurkey;

public class DuckTestDrive {
    public static void main(String[] args) {
        MallardDuck duck = new MallardDuck();    //先创建一只鸭子

        WildTurkey turkey = new WildTurkey();    //和一只火鸡
        Duck turkeyAdapter = new TurkeyAdapter(turkey);    //然后将火鸡装进一个火鸡适配器中，使它看起来像一只鸭子

        System.out.println("The Turkey says...");    //测试火鸡
        turkey.gobble();
        turkey.fly();

        System.out.println("\nThe Duck says...");    //测试鸭子
        testDuck(duck);

        System.out.println("\nThe TurkeyAdapter says...");    //重要的测试来了，试着传入一只假装鸭子的火鸡
        testDuck(turkeyAdapter);
    }

    static void testDuck(Duck duck) {
        duck.quack();
        duck.fly();
    }
}
