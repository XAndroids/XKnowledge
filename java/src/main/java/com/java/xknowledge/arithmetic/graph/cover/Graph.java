package com.java.xknowledge.arithmetic.graph.cover;

/**
 * 定义图的结构
 */
class Graph {
    int size;//图中节点的个数
    String[] nodes;//定义数组，保存图中节点信息
    int[][] edges;//定义矩阵，保留图中边的信息

    /**
     *      A B C D E F G
     *   A  0 0 1 1 0 1 0
     *   B  0 0 1 0 0 0 0
     *   C  1 1 0 1 0 0 0
     *   D  1 0 1 0 0 0 0
     *   E  0 0 0 0 0 0 1
     *   F  1 0 0 0 0 0 1
     *   G  0 0 0 0 1 1 0
     */
    public Graph() {
        //初始化节点
        nodes = new String[]{"A", "B", "C", "D", "E", "F", "G"};
        //初始化节点个数
        size =  nodes.length;

        //初始化边
        final int A = 0, B = 1, C = 2, D = 3, E = 4, F = 5, G = 6;
        edges = new int[size][size];
        edges[A][C] = 1;
        edges[A][D] = 1;
        edges[A][F] = 1;
        edges[B][C] = 1;
        edges[C][A] = 1;
        edges[C][B] = 1;
        edges[C][D] = 1;
        edges[D][A] = 1;
        edges[D][C] = 1;
        edges[E][G] = 1;
        edges[F][A] = 1;
        edges[F][G] = 1;
        edges[G][E] = 1;
        edges[G][F] = 1;
    }
}
