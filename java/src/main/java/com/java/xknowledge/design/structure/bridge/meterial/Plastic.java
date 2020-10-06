package com.java.xknowledge.design.structure.bridge.meterial;

/**
 * 塑料材质
 */
public class Plastic implements Material {
    @Override
    public void draw() {
        System.out.println("用塑料盒");
    }
}
