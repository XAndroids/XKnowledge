package com.java.xknowledge.design.action.template.headfirst;

import com.java.xknowledge.design.action.template.headfirst.coffee.Coffee;
import com.java.xknowledge.design.action.template.headfirst.coffee.Tea;
import com.java.xknowledge.design.action.template.headfirst.hook.CoffeeWithHook;
import com.java.xknowledge.design.action.template.headfirst.hook.TeaWithHook;

/**
 * 模板方法模式
 * 参考：《Head First 设计模式》
 */
public class BeverageTestDrive {
    public static void main(String[] args) {

        TeaWithHook teaHook = new TeaWithHook();
        CoffeeWithHook coffeeHook = new CoffeeWithHook();

        System.out.println("\nMaking tea...");
        teaHook.prepareRecipe();

        System.out.println("\nMaking coffee...");
        coffeeHook.prepareRecipe();
    }
}
