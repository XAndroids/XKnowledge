package com.java.xknowledge.arithmetic.graph.cover;

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
}
