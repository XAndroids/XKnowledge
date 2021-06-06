package com.java.xknowledge.leetcode.tree.easy.levelorder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 102. 二叉树的层序遍历/剑指
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
//        order(root, 1, levelOrderList);
        order2(root, levelOrderList);
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

    /**
     * 队列实现，每次遍历同一个队列的所有节点，添加下一个队列的节点
     */
    public static void order2(TreeNode root, List<List<Integer>> levelOrderList) {
        //辅助队列，默认先添加root节点
        Queue<TreeNode> helperQueue = new LinkedList<>();
        helperQueue.add(root);

        while (!helperQueue.isEmpty()) {
            //每次循环遍历上一层的节点
            List<Integer> subResult = new ArrayList<>();
            int queueSize = helperQueue.size();
            for (int i = 0; i < queueSize; i++) {
                TreeNode node = helperQueue.poll();
                if (node != null) {
                    //添加本层级节点值
                    subResult.add(node.val);

                    //添加下一层级的节点
                    helperQueue.add(node.left);
                    helperQueue.add(node.right);
                }
            }

            //如果当前层级有节点，则添加到结果集合
            if(subResult.size() > 0){
                levelOrderList.add(subResult);
            }
        }
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
