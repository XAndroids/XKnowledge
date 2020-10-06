package com.java.xknowledge.design.structure.composite;
import java.util.List;
/**
 * 抽象节点
 */
abstract class Node {
    private String name;

    public Node(String name) {
        this.name = name;
    }

    //定义抽象方法，获取孩子节点
    public abstract List<Node> getChildren();
}
