package com.java.xknowledge.design.structure.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 子节点
 */
class DistrictNode extends Node {
    //包含子节点的节点
    private List<Node> children = new ArrayList<>();

    public DistrictNode(String name) {
        super(name);
    }

    @Override
    public List<Node> getChildren() {
        return children;    //获取孩子节点
    }

    //增加子节点
    public void addChild(Node node) {
        children.add(node);
    }
}
