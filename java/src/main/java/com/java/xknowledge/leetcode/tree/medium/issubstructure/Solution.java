package com.java.xknowledge.leetcode.tree.medium.issubstructure;

/**
 * 剑指 Offer 26. 树的子结构
 * 链接：https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof/
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

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        //参数校验
        if (A == null || B == null) {
            return false;
        }

        //如果根节点相等，则才开始匹配子结构
        if (A.val == B.val) {
            if (matchSubStructure(A, B)) {
                return true;
            }
        }

        //如果没有匹配，则继续从左右子树匹配B
        return isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    //匹配子结构，注意和匹配子树区分
    private boolean matchSubStructure(TreeNode a, TreeNode b) {
        //如果b==null，无论a是什么都为true
        if (b == null) {
            return true;
        }

        //如果b!=null&&a==null || a.val!=b.val故为false
        if (a == null || a.val != b.val) {
            return false;
        }

        //递归回调左右子树
        return matchSubStructure(a.left, b.left) && matchSubStructure(a.right, b.right);
    }
}
