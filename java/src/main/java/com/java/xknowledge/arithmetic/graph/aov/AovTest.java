package com.java.xknowledge.arithmetic.graph.aov;

/**
 * Aov拓步排序算法实践
 * 参考：《享学1：16图的的拓补算法，如何排查出大型工程中影响成败的关键项目》
 */
class AovTest {
    public static void main(String[] args) {
        Aov aov = new Aov();
        aov.flush();//计算节点的入度

//        aov.getPath();//深度优先遍历查找
//        aov.getPath2();//广度优先遍历查找

        aov.exeKey();
    }
}
