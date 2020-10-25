package com.java.xknowledge.arithmetic.graph.cover;

import org.omg.CORBA.INTERNAL;

import java.util.ArrayList;
import java.util.List;

/**
 * 图的遍历
 */
class GraphCover extends Graph {
    //遍历标识，标记访问过的节点，防止死循环
    private int[] visit = new int[size];

    /**
     * 深度优先遍历
     * 一条路走到黑，不撞南墙不回头
     * 对每一个可能的分支路径深入到不能再深入位置
     *
     * @param start 开始访问节点
     */
    public void DeepFirst(int start) {
        visit[start] = 1;//标记1表示该顶点已经被访问过了
        System.out.println("齐天大圣到—>" + this.nodes[start] + "一游"); //输出节点数据

        for (int i = 0; i < size; i++) {
            //如果改节点visit[i]没有访问过，并且从开始节点start至这个节点i的边存在，
            if (edges[start][i] == 1 && visit[i] == 0) {
                //则可以递归进一步从该节点遍历
                DeepFirst(i);
            }
            //如果该节点的所有边都访问过，或者邻接节点被访问过，则跳出该层递归，回溯到上一个递归其实节点进一步遍历
        }
    }

    /**
     * 广度优先遍历
     * 广度优先搜索遍历图的过程中以v为起点，由近至远
     * 依次访问和v有路径相通且路径长度为1，2....的顶点
     */
    public void BreadthFirst1(List<Integer> tmpNodeList) {
        List<Integer> lastTmpNodeList = new ArrayList<>();

        for (int node : tmpNodeList) {
            visit[node] = 1;
            System.out.println("齐天大圣到—>" + this.nodes[node] + "一游"); //输出节点数据

            for (int i = 0; i < size; i++) {
                //遍历当前访问节点的邻接节点，且没有被访问，且不在本次"层级"中，则加入在一个"层级"递归访问
                if (edges[node][i] == 1 && visit[i] == 0 && !tmpNodeList.contains(i)) {
                    lastTmpNodeList.add(i);
                }
            }
        }

        if (lastTmpNodeList.size() > 0) {
            BreadthFirst1(lastTmpNodeList);
        }
    }

    public void BreadthFirst2() {

    }
}
