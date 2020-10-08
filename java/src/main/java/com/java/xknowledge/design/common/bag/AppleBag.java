package com.java.xknowledge.design.common.bag;

/**
 * 苹果包装实现类
 */
public class AppleBag implements Bag {
    @Override
    public void pack() {
        System.out.print("--苹果使用纸箱包装");
    }
}
