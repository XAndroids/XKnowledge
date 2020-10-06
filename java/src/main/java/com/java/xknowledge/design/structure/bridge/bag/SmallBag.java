package com.java.xknowledge.design.structure.bridge.bag;

/**
 * 小号袋子
 */
class SmallBag extends BagAbstraction {
    @Override
    public void pick() {
        this.material.draw();
        System.out.println("采摘了一小袋子");
    }
}
