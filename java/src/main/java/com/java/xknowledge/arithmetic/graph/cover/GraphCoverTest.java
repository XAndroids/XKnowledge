package com.java.xknowledge.arithmetic.graph.cover;

import java.util.ArrayList;
import java.util.List;

/**
 * 图的遍历
 * 参考：《享学1：12图论（中），图的搜索，深度和广度优先方式如何遍历整个图》
 */
class GraphCoverTest {
    public static void main(String[] args) {
        //深度遍历
        GraphCover graphCover = new GraphCover();
//        graphCover.DeepFirst(0);

        //广度遍历
        List<Integer> startList = new ArrayList<>();
        startList.add(0);
        graphCover.BreadthFirst1(startList);
    }
}
