package com.java.xknowledge.leetcode.tree.maxdepth;

/**
 * 104. 二叉树的最大深度
 * 给定一个二叉树，找出其最大深度。
 *
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最大深度 3 。
 * 链接：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree
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

    public static int maxDepth(TreeNode root) {
        return order(root);
    }

    /**
     * 递归遍历：
     * 从上往下开始计算，能算出每个叶子节点的深度，但是无法算出整个树的深度
     * 需要从下往上回调开始计算，树的深度= Max.(左子树深度,右子树深度)+1为当前节点的深度
     */
    public static int order(TreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        }

        return Math.max(order(treeNode.left), order(treeNode.right)) + 1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.left.left = new TreeNode(7);

        System.out.print(maxDepth(root));
    }
}
