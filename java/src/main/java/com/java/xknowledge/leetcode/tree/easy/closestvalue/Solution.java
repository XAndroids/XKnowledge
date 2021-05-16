package com.java.xknowledge.leetcode.tree.easy.closestvalue;

/**
 * 270. 最接近的二叉搜索树值
 * 链接：https://leetcode-cn.com/problems/closest-binary-search-tree-value/
 */
class Solution {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    //最小距离值
    private int resultInt;
    //最小差值
    private double minDif = Integer.MAX_VALUE;

    public int closestValue(TreeNode root, double target) {
        closestValueHelper(root, target);
        return resultInt;
    }

    public void closestValueHelper(TreeNode root, double target) {
        //如果寻找到叶子节点，则删除
        if (root == null) return;

        //计算和当前节点的差值，如果最小则更新最小差值，和最小距离值
        double curDif = Math.abs(root.val - target);
        if (curDif < minDif) {
            minDif = curDif;
            resultInt = root.val;
        }

        //在递归从左右子树查找
        if (target > root.val) {
            closestValueHelper(root.right, target);
        } else {
            closestValueHelper(root.left, target);
        }
    }
}
