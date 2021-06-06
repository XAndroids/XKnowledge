package com.java.xknowledge.leetcode.tree.easy.levelorder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Offer 32 - II. 从上到下打印二叉树 II
 * 链接：https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof/
 */
class Solution2 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        levelOrderHelper(root, result);
        return result;
    }

    public void levelOrderHelper(TreeNode root, List<List<Integer>> result) {
        //辅助队列，默认先添加root节点
        Queue<TreeNode> helperQueue = new LinkedList<>();
        helperQueue.add(root);

        while (!helperQueue.isEmpty()) {
            //每次循环遍历上一层的节点
            LinkedList<Integer> subResult = new LinkedList<>();
            int queueSize = helperQueue.size();
            for (int i = 0; i < queueSize; i++) {
                TreeNode node = helperQueue.poll();
                if (node != null) {
                    //奇数从左到右，偶数层从右到左
                    if (result.size() % 2 != 0) {
                        subResult.addFirst(node.val);
                    } else {
                        subResult.addLast(node.val);
                    }

                    //添加下一层级的节点
                    helperQueue.add(node.left);
                    helperQueue.add(node.right);
                }
            }

            //如果当前层级有节点，则添加到结果集合
            if (subResult.size() > 0) {
                result.add(subResult);
            }
        }
    }
}
