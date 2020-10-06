package com.java.xknowledge.design.structure.bridge.bag;

/**
 * 大号袋子
 */
public class BigBag extends BagAbstraction {
    @Override
    public void pick() {
        this.material.draw();
        System.out.println("采摘了一大袋子");
    }
}
