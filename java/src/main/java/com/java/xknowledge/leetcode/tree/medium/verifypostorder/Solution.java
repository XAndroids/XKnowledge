package com.java.xknowledge.leetcode.tree.medium.verifypostorder;

/**
 * 剑指 Offer 33. 二叉搜索树的后序遍历序列
 * 链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/
 */
class Solution {
    //递归判断法
    public boolean verifyPostorder(int[] postorder) {
        return verifyPostorderHelper(postorder, 0, postorder.length);
    }

    public boolean verifyPostorderHelper(int[] postorder, int start, int end) {
        if (start >= end) return true;

        //寻找左子树区分节点位置
        int partion = start;
        while (postorder[partion] < postorder[end]) {
            partion++;
        }
        int middle = partion;
        //寻找右子树区分节点位置
        while (postorder[partion] > postorder[end]) {
            partion++;
        }

        //partion == end当前节点满足二叉搜搜树，左子树和右子树满足二叉搜索树
        return partion == end && verifyPostorderHelper(postorder, start, middle - 1)
                && verifyPostorderHelper(postorder, middle, end - 1);
    }
}
