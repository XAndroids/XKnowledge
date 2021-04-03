package com.java.xknowledge.leetcode.tree.inorder;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 94.二叉树的中序遍历
 * 给定一个二叉树的根节点 root ，返回它的 中序 遍历。
 * 示例 1：
 * 输入：root = [1,null,2,3]
 * 输出：[1,3,2]
 * 示例 2：
 * <p>
 * 输入：root = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：root = [1]
 * 输出：[1]
 * 示例 4：
 * <p>
 * 输入：root = [1,2]
 * 输出：[2,1]
 * 示例 5：
 * <p>
 * 输入：root = [1,null,2]
 * 输出：[1,2]
 * <p>
 * 链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal
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
     * 递归解法
     */
    public static List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> resultList = new ArrayList<>();
        inorder(root, resultList);
        return resultList;
    }

    /**
     * 中序遍历
     */
    public static void inorder(TreeNode root, List<Integer> result) {
        if (root == null) {//注意：递归退出条件只有一个，没有必要再判断root.left和root.right的null！！
            return;
        }
        inorder(root.left, result);
        result.add(root.val);//中序遍历，以root为标准，从左到右：left-root-right
        inorder(root.right, result);
    }


    /**
     * 迭代方法
     * 思路：模拟虚拟机迭代的方法调用栈
     * 递归的调用方式：不断左子子树入栈，如果没有则输出当前节点，立即转向右子树重复，此堆栈
     */
    public static List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> resultList = new ArrayList<>();//树的遍历结果集合
        Deque<TreeNode> stackDeque = new LinkedList<>();//控制调用的对阵
        while (root != null || !stackDeque.isEmpty()) {
            if (root != null) {
                stackDeque.push(root);//当前节点入栈，不断深入左子树
                root = root.left;//不断深入左子树
            } else {
                TreeNode temp = stackDeque.pop();
                resultList.add(temp.val);//没有左子树输出当前节点，立即转向右子树
                root = temp.right;
            }
        }
        return resultList;
    }


    /**
     * 前序遍历：递归算法
     */
    public static void leftorder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }

        result.add(root.val);
        leftorder(root.left, result);
        leftorder(root.right, result);
    }

    public static List<Integer> leftorderTraversal1(TreeNode root) {
        List<Integer> resultList = new ArrayList<>();
        leftorder(root, resultList);
        return resultList;
    }

    /**
     * 前序遍历：迭代算法
     */
    public static List<Integer> leftorderTraversal2(TreeNode root) {
        List<Integer> resultList = new ArrayList<>();
        Deque<TreeNode> stackDeque = new LinkedList<>();
        while (root != null || !stackDeque.isEmpty()) {
            if (root != null) {
                resultList.add(root.val);
                stackDeque.push(root);
                root = root.left;
            } else {
                TreeNode temp = stackDeque.pop();
                root = temp.right;
            }
        }
        return resultList;
    }


    public static void main(String[] args) {
        //递归解法
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        System.out.println(inorderTraversal1(root).toString());

        //遍历解法
        System.out.println(inorderTraversal2(root).toString());


        System.out.println(leftorderTraversal1(root).toString());
        System.out.println(leftorderTraversal2(root).toString());
    }
}
