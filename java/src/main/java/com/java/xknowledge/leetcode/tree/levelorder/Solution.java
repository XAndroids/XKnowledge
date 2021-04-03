package com.java.xknowledge.leetcode.tree.levelorder;

import java.util.ArrayList;
import java.util.List;

/**
 * 102. 二叉树的层序遍历
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 * 示例：
 * 二叉树：[3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层序遍历结果：
 *
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 *
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal
 */
class Solution {
    public static class TreeNode {
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

    /**
     * 递归遍历二叉树
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> levelOrderList = new ArrayList<>();//存储层级的集合，索引index为每个层级
        order(root, 1, levelOrderList);
        return levelOrderList;
    }

    /**
     * 递归遍历二叉树
     */
    public static void order(TreeNode root, int level, List<List<Integer>> levelOrderList) {
        if (root == null) {//递归退出条件
            return;
        }

        if (levelOrderList.size() < level) {//如果当前层级的保存集合还不存在，则创建新层级集合
            levelOrderList.add(new ArrayList<>());
        }
        levelOrderList.get(level - 1).add(root.val);//将当前元素添加到指定的层级上

        order(root.left, level + 1, levelOrderList);//递归左右子树
        order(root.right, level + 1, levelOrderList);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.println(levelOrder(root));
    }
}
