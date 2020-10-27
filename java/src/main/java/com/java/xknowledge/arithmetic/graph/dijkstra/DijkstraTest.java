package com.java.xknowledge.arithmetic.graph.dijkstra;

/**
 * Dijstra算法测试
 * 参考：《享学1：13图论（下），图的最短路径，如何规划首都到全国各大城市的最快网络》
 */
class DijkstraTest {
    public static void main(String[] args) {
        Dijkstra dijkstra = new Dijkstra();
        dijkstra.search(0);
    }
}
