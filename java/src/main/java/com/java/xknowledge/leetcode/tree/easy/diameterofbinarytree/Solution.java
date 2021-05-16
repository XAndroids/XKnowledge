package com.java.xknowledge.leetcode.tree.easy.diameterofbinarytree;

/**
 * 543. 二叉树的直径
 * 链接：https://leetcode-cn.com/problems/diameter-of-binary-tree/
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

        int maxDiameter;//树的最大路径，全部变量用于保存最大值

        public int diameterOfBinaryTree(TreeNode root) {
            checkTreeDeep(root);
            return maxDiameter - 1;
        }

        /**
         * 遍历树中每个节点，计算深度的同时，也计算各个节点为根的子树的最大路径，最后取最大值
         */
        public int checkTreeDeep(TreeNode root) {
            if (root == null) {
                return 0;
            }

            int deepLeft = checkTreeDeep(root.left);//递归遍历树中的每一个节点的深度
            int deepRight = checkTreeDeep(root.right);

            //同时计算子树最大路径，左子树深度+右子树深度+1
            //树遍历完后，所有节点最大的路径即树的最大路径，记住不一定经过根节点！！！！
            maxDiameter = Math.max(maxDiameter, deepLeft + deepRight + 1);

            return Math.max(deepLeft, deepRight) + 1;//每个节点的深度=左子树深度和右子树深度最大值+1
        }
    }
}
