package com.java.xknowledge.design.structure.bridge.bag;

import com.java.xknowledge.design.structure.bridge.meterial.Material;

/**
 * 袋子抽象类，包含材质成员用于动态"扩展"，子类用于大小扩展
 */
public abstract class BagAbstraction {
    //材质成员
    Material material;

    public void setMaterial(Material material) {
        this.material = material;
    }

    /**
     * 采摘方法
     */
    public abstract void pick();
}
