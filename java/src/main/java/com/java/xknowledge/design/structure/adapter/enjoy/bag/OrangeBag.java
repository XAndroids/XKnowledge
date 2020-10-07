package com.java.xknowledge.design.structure.adapter.enjoy.bag;

/**
 * 橘子包装实现类
 */
public class OrangeBag implements Bag {
    @Override
    public void pack() {
        System.out.println("--桔子使用网兜包装");
    }
}
