package com.java.xknowledge.leetcode.tree.medium.levelorder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 剑指 Offer 32 - I. 从上到下打印二叉树-中等
 * 链接：https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-lcof/
 */
class Solution {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int[] levelOrder(TreeNode root) {
        //校验参数
        if (root == null) return new int[0];

        //队列遍历每个节点，默认添加root根节点
        List<Integer> resultList = new ArrayList<>();
        Queue<TreeNode> helpQueue = new LinkedList<>();
        helpQueue.add(root);
        //如果队列不为空，则继续遍历
        while (!helpQueue.isEmpty()) {
            //获取当前节点，如果节点不为null，添加值并将left和right节点添加队列
            TreeNode treeNode = helpQueue.poll();
            if (treeNode != null) {
                resultList.add(treeNode.val);
                helpQueue.add(treeNode.left);
                helpQueue.add(treeNode.right);
            }
        }

        //将List<Integer>转换int[]
        int[] result = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            result[i] = resultList.get(i);
        }

        return result;
    }
}
