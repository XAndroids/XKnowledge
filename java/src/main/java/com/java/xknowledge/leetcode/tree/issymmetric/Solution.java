package com.java.xknowledge.leetcode.tree.issymmetric;

/**
 * 101. 对称二叉树
 * 链接：https://leetcode-cn.com/problems/symmetric-tree/
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

    public boolean isSymmetric(TreeNode root) {
        //递归思想：将同一层级的两个对称节点，互为比较判断
        return isSymmetricCheck(root.left, root.right);
    }

    public boolean isSymmetricCheck(TreeNode root1, TreeNode root2) {
        //递归到节点子节点都为null，返回true
        if (root1 == null && root2 == null) {
            return true;
        }

        {//如果节点单一为null，或者值不相同，就递归返回false，不再继续递归
            if (root1 == null || root2 == null || root1.val != root2.val)
                return false;
        }

        //如果当前节点的值相同，则继续将节点1左和节点2右继续判断镜像
        return isSymmetricCheck(root1.left, root2.right) && isSymmetricCheck(root1.right, root2.left);
    }
}
