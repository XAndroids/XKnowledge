package com.java.xknowledge.design.structure.bridge.bag;

/**
 * 中号袋子
 */
public class MidBag extends BagAbstraction {
    @Override
    public void pick() {
        this.material.draw();
        System.out.println("采摘了一中袋子");
    }
}
