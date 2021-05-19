package com.java.xknowledge.leetcode.array.easy.findnumberin2darray;

/**
 * 剑指 Offer 04. 二维数组中的查找
 * 链接：https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/
 */
class Solution {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        //如果不是二维数组，则返回false
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;

        //从左下角开始遍历
        int i = 0, j = matrix[0].length - 1;
        //如果遍历到边界还没有，则即没有
        while (i < matrix.length && j >= 0) {
            //根据大小，修改i和j的索引
            if (matrix[i][j] > target) {
                j--;
            } else if (matrix[i][j] < target) {
                i++;
            } else {
                return true;
            }
        }

        //如果遍历完毕还未找到，则就没有
        return false;
    }
}
