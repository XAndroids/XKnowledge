package com.java.xknowledge.leetcode.tree.kthlargest;

import java.util.Set;
import java.util.TreeSet;

/**
 * 剑指 Offer 54. 二叉搜索树的第k大节点
 * 链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof/
 */
class Solutaion {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //TreeSet有序Set集合
    static Set<Integer> integerTreeSet = new TreeSet<>();

    public static int kthLargest(TreeNode root, int k) {
        traverseTree(root);//递归遍历二叉树，保存在TreeSet中，顺序保存

        //获取TreeSet对应有序数组
        Integer[] sortTreeNode = new Integer[integerTreeSet.size()];
        integerTreeSet.toArray(sortTreeNode);

        return sortTreeNode[integerTreeSet.size() - k];//返回第k大元素
    }

    private static void traverseTree(TreeNode root) {
        if (root == null) {
            return;
        }

        integerTreeSet.add(root.val);

        traverseTree(root.left);
        traverseTree(root.right);
    }

    public static void main(String[] args) {
        TreeNode rootNode = new TreeNode(3);
        TreeNode leftNode = new TreeNode(1);
        leftNode.right = new TreeNode(2);
        rootNode.left = leftNode;
        rootNode.right = new TreeNode(4);

        System.out.println(kthLargest(rootNode, 1));
    }
}
