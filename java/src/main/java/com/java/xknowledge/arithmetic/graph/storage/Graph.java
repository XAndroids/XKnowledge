package com.java.xknowledge.arithmetic.graph.storage;

/*
 * 图的存储结构
 * 参考：《享学1：11图论（上），图的概念，如何存储表示微博、微信等社交网络的关系图》
 */
public class Graph {
    //节点数目
    protected int size;
    //定义数组，保存顶点信息
    protected String[] nodes;

    //定义矩阵保存顶点之间边的信息，比如权和方向
    protected int[][] edges;

    /**
     * vo v1 v2 v3
     * vo  0  1  1  1
     * v1  1  0  1  0
     * v2  1  1  0  1
     * v3  1  0  1  0
     */
    public Graph() {
        size = 4;

        //初始化顶点
        nodes = new String[size];
        for (int i = 0; i < size; i++) {
            nodes[i] = String.valueOf(i);
        }

        //初始化边
        edges = new int[size][size];
        edges[0][1] = 1;
        edges[0][2] = 1;
        edges[0][3] = 1;
        edges[1][0] = 1;
        edges[1][2] = 1;
        edges[2][0] = 1;
        edges[2][1] = 1;
        edges[2][3] = 1;
        edges[3][0] = 1;
        edges[3][2] = 1;
    }

    public static void main(String[] args) {
        Graph graph = new Graph();
    }
}