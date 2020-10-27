package com.java.xknowledge.arithmetic.graph.aov;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import io.reactivex.internal.operators.single.SingleTakeUntil;

class Aov {
    //节点数目
    protected int size;
    //定义数组，保存顶点信息
    protected String[] nodes;

    //定义矩阵保存顶点信息
    protected int[][] edges;

    //每个顶点入度数据
    private int[] eSize;
    private int[] fast;//最早时间
    private int[] last;//最晚时间

    public Aov() {
        init();
    }

    public void init() {
        //初始化顶点
        nodes = new String[]{"AA", "A", "B", "C", "D", "E", "F", "G", "H", "M", "K", "N"};
        //节点编号-常量
        final int AA = 0, A = 1, B = 2, C = 3, D = 4, E = 5, F = 6, G = 7, H = 8, M = 9, K = 10, N = 11;
        size = nodes.length;

        edges = new int[size][size];
        edges[AA][A] = 3;
        edges[AA][B] = 2;
        edges[AA][C] = 5;
        edges[A][D] = 4;
        edges[B][G] = 2;
        edges[B][E] = 3;
        edges[C][E] = 2;
        edges[C][F] = 3;
        edges[D][G] = 1;
        edges[E][K] = 1;
        edges[E][M] = 8;
        edges[F][K] = 4;
        edges[G][H] = 2;
        edges[H][M] = 3;
        edges[K][N] = 2;
        edges[M][N] = 3;

        fast = new int[size];
        last = new int[size];
    }

    /**
     * 计算每个节点的入度
     */
    void flush() {
        //初始化每个节点的入度数组
        eSize = new int[size];

        //遍历所有节点，计算每个节点的入度数
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (edges[j][i] > 0) {
                    eSize[i]++;
                }
            }
        }
    }

    /**
     * 获取Aov排序路径
     * 广度优先搜索遍历
     */
    int[] getPath() {
        int count = 0;
        int[] path = new int[size];

        //将入度为0的节点添加到队列中
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            if (eSize[i] == 0) {
                queue.offer(i);
            }
        }

        //遍历入队节点的邻接节点，入度-1
        while (true) {
            Integer node = queue.poll();
            if (node == null) {//如果没有邻接接节点，则排除完毕
                break;
            }

            //添加Aov路径数组
            System.out.print("---->" + nodes[node]);
            path[count++] = node;

            for (int i = 0; i < size; i++) {
                if (edges[node][i] > 0) {//和入队节点node是邻接节点
                    eSize[i]--;//邻接节点入度-1
                    if (eSize[i] == 0) {
                        queue.offer(i);//如果入度为0，则添加到队列继续遍历
                    }
                }
            }
        }

        return path;
    }

    /**
     * 获取Aov排序路径
     * 深度优先搜索遍历
     */
    int[] getPath2() {
        int count = 0;
        int[] path = new int[size];

        //将入度为0的节点添加到队列中
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < size; i++) {
            if (eSize[i] == 0) {
                stack.push(i);
            }
        }

        //遍历入队节点的邻接节点，入度-1
        while (!stack.empty()) {
            Integer node = stack.pop();

            //添加Aov路径数组
            System.out.print("---->" + nodes[node]);
            path[count++] = node;

            for (int i = 0; i < size; i++) {
                if (edges[node][i] > 0) {//和入队节点node是邻接节点
                    eSize[i]--;//邻接节点入度-1
                    if (eSize[i] == 0) {
                        stack.push(i);//如果入度为0，则添加到队列继续遍历
                    }
                }
            }
        }

        return path;
    }

    /**
     * 计算图从node节点开始，各个节点的最早执行时间
     *
     * @param node 图的开始节点node
     */
    private void exeFast(int node) {
        for (int i = 0; i < size; i++) {
            if (edges[node][i] > 0) {//如果i是邻接节点
                int cost = fast[node] + edges[node][i];//到i节点最短路径为到node的最短路径+node到i节点的路径长度
                if (cost > fast[i]) {//如果达到i节点的时间更大，则保留为i节点最早执行时间
                    fast[i] = cost;
                    exeFast(i);//递归查找i节点邻接节点的最早执行时间
                }
            }
        }
    }

    /**
     * 计算图从node节点开始，各个节点最晚执行时间，从结尾推断
     *
     * @param node 图的开始节点node
     */
    private void exeLast(int node) {
        for (int i = 0; i < size; i++) {
            if (edges[i][node] > 0) {
                int cost = last[node] - edges[i][node];
                if (cost < last[i]) {
                    last[i] = cost;
                    exeLast(i);
                }
            }
        }
    }

    public void exeKey() {
        int[] path = getPath();
        int start = path[0], end = path[size - 1];

        exeFast(start);
        for (int i = 0; i < size; i++) {//初始化成工程最大值
            last[i] = fast[end];
        }
        exeLast(end);

        for (int i = 0; i < size; i++) {
            int node = path[i];
            if (fast[node] == last[node]) {
                System.out.print("--->" + nodes[node]);
            }
        }
    }
}
