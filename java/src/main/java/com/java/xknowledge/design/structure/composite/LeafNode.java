package com.java.xknowledge.design.structure.composite;
import java.util.List;

/**
 * 叶子节点
 */
class LeafNode extends Node{
    public LeafNode(String name) {
        super(name);
    }

    @Override
    public List<Node> getChildren() {
        return null;    //叶子节点没有子节点了
    }
}
