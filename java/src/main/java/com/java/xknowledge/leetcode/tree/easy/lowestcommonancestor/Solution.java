package com.java.xknowledge.leetcode.tree.easy.lowestcommonancestor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer 68 - II. 二叉树的最近公共祖先
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

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //如果节点到了叶子节点，则返回null
        //如果节点是p或者q节点，则返回改节点
        if (root == null || root == p || root == q) {
            return root;
        }

        //后续遍历每个节点root，能从下往上判断最近公共父节点，判断左右子树是否包含q,p
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        //如果左右子为null，则root的左右子树都不包含pq，返回null
        if (left == null && right == null) return null;
        //如果左子树不包含pq，直接返回右子树:
        //1.qp其中一个节点在右子树，在后续节点找到另外一个子树，返回这个root；
        //2.qp都在右子树，则right为最近公共组件返回；
        if (left == null) return right;
        //如果右子树不包含qp，同上；
        if (right == null) return left;
        //如果左右都不为null，pq在root的左右子树上，root为最近公共祖先

        return root;
    }
}
