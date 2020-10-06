package com.java.xknowledge.design.structure.bridge.bag;

import com.java.xknowledge.design.structure.bridge.meterial.Material;

/**
 * 采摘袋子有两个属性：大小和材质
 * 采摘袋的主要属性是：大小，附属性：材质
 * 剥离附属性，放入扩展实体中去
 * <p>
 * 袋子抽象类，附属性材质：成员用于动态"扩展"，大小属性：子类扩展
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
