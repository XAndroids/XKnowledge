package com.java.xknowledge.leetcode.tree.easy.findtarget;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * 653. 两数之和 IV - 输入 BST
 * 链接：https://leetcode-cn.com/problems/two-sum-iv-input-is-a-bst/
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

    //题解1：先中序遍历得到有序排列，然后使用left和right双指针查找目标元素，如果有目标元素则返回true，没有返回false
    public boolean findTarget(TreeNode root, int k) {
        //参数校验
        if (root == null) return false;

        List<Integer> treeNodes = new ArrayList<>();
        //中序遍历获取节点的有序集合
        middleSearchTree(root, treeNodes);

        //通过左右两个指针，通过sum判断"遍历"左右指针
        int left = 0;
        int right = treeNodes.size() - 1;
        while (left < right) {
            int sum = treeNodes.get(left) + treeNodes.get(right);
            if (sum > k) {//如果left和right>k，则right左移动
                right--;
            } else if (sum < k) {//如果left和right<k，则left右移动
                left++;
            } else {
                return true;
            }
        }
        return false;
    }

    //深度遍历二叉搜索树
    private void middleSearchTree(TreeNode root, List<Integer> treeNodes) {
        if (root == null) return;
        middleSearchTree(root.left, treeNodes);
        treeNodes.add(root.val);
        middleSearchTree(root.right, treeNodes);
    }


    //题解2：与数据遍历一样，通过深度遍历遍历树，在遍历过程中通过HashMap存储的目标值和索引查找匹配的值，如果有返回
    //true
    public boolean findTarget2(TreeNode root, int k) {
        //目标值缓存集合，只判断是否包含目标组合故只需要Set集合存储目标值
        Set<Integer> targetSet = new HashSet<>();
        return find(root, k, targetSet);
    }

    public boolean find(TreeNode root, int k, Set<Integer> targetSet) {
        if (root == null) return false;//如果遍历到叶子节点，还未找到匹配的两个值则返回false

        if (targetSet.contains(root.val)) {//如果节点是要找的目标节点，则返回true不再继续遍历
            return true;
        } else {
            targetSet.add(k - root.val);//如果不是目标节点，则添加新的目标节点
        }

        //递归遍历左右子树查找目标节点
        return find(root.left, k, targetSet) || find(root.right, k, targetSet);
    }


    //题解3：通过广度遍历遍历树，其余同题解2算法一样
    public boolean findTarget3(TreeNode root, int k) {
        if (root == null) return false;

        Set<Integer> targetSet = new HashSet<>();
        Queue<TreeNode> traverseQueue = new LinkedList<>();
        traverseQueue.add(root);

        while (!traverseQueue.isEmpty()) {
            TreeNode treeNode = traverseQueue.poll();
            if (treeNode != null) {
                if (targetSet.contains(treeNode.val)) {//如果节点是要找的目标节点，则返回true不再继续遍历
                    return true;
                } else {
                    targetSet.add(k - treeNode.val);//如果不是目标节点，则添加新的目标节点
                }

                traverseQueue.add(treeNode.left);
                traverseQueue.add(treeNode.right);
            }
        }

        return false;
    }
}
