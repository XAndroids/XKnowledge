package com.java.xknowledge.design.structure.adapter.headfirst.turkey;

/**
 * 火鸡的具体实现
 */
public class WildTurkey implements Turkey {
    public void gobble() {
        System.out.println("Gobble gobble");    //和鸭子一样，只是具体打印动作说明
    }

    public void fly() {
        System.out.println("I'm flying a short distance");
    }
}
