package com.java.xknowledge.design.structure.bridge.meterial;

/**
 * 纸质材质
 */
public class Paper implements Material {
    @Override
    public void draw() {
        System.out.println("用纸盒");
    }
}
