package com.java.xknowledge.leetcode.array.easy.spiralorder;

/**
 * 剑指 Offer 29. 顺时针打印矩阵
 * 链接：https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/
 */
class Solution {
    //思路：遍历所有节点
    //totle = m * n，节点总数控制循环访问的结束
    //numsAccessed = new int[m][n]标记节点是否访问;
    //direction = {{0,1},{1,0},{0,-1},{-1,0}}来控制遍历的方向
    //从[0,0]节点开始，direction[0]，开始遍历。如果节点到边界或者到已经访问过的节点，则更新另一个方向继续遍历
    public int[] spiralOrder(int[][] matrix) {
        //参数校验
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return new int[0];

        //计算和声明遍历节点总数、访问节点记录数组、遍历方向数组等
        int rowLength = matrix.length, columLength = matrix[0].length;
        int totalLength = rowLength * columLength;
        int[] accessedResult = new int[totalLength];
        boolean[][] accessedMatrix = new boolean[rowLength][columLength];
        int[][] direction = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        int row = 0, column = 0, directionIndex = 0;
        for (int i = 0; i < totalLength; i++) {
            //将遍历元素添加访问结果结合，更新访问标记数组
            accessedResult[i] = matrix[row][column];
            accessedMatrix[row][column] = true;

            //计算下一个遍历节点坐标，增加当前遍历方向数组
            int nextRow = row + direction[directionIndex][0];
            int newColumn = column + direction[directionIndex][1];
            if (nextRow >= rowLength || newColumn >= columLength || nextRow < 0 || newColumn < 0 ||
                    accessedMatrix[nextRow][newColumn]) {
                //如果遍历的节点到达边界，或者已访问过的节点，则进行转向，重新计算下一个遍历节点坐标
                directionIndex = (directionIndex + 1) % 4;
                row = row + direction[directionIndex][0];
                column = column + direction[directionIndex][1];
            } else {
                //如果没有转向，则计算的新点坐标继续访问
                row = nextRow;
                column = newColumn;
            }
        }

        return accessedResult;
    }
}
