package com.java.xknowledge.arithmetic.tree.traverse.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * 树的深度遍历
 * 参考：https://www.cnblogs.com/liyao0312/p/11401019.html
 */
class DFS {
    static class TreeNode {
        int data;
        List<TreeNode> childNodeList;

        public TreeNode(int data) {
            this.data = data;
            this.childNodeList = new ArrayList<>();
        }

        public void addChildNode(TreeNode treeNode) {
            childNodeList.add(treeNode);
        }
    }

    /**
     * 递归实现
     */
    static void dfs(TreeNode rootNode) {
        if (rootNode == null) {
            return;
        }

        System.out.println(rootNode.data);//输出当前节点值

        if (rootNode.childNodeList != null) {
            for (int i = 0; i < rootNode.childNodeList.size(); i++) {
                dfs(rootNode.childNodeList.get(i));//比例递归所有孩子节点
            }
        }
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(0);
        TreeNode childNode1 = new TreeNode(1);
        TreeNode childNode11 = new TreeNode(4);
        childNode1.addChildNode(childNode11);
        TreeNode childNode2 = new TreeNode(2);
        TreeNode childNode21 = new TreeNode(5);
        childNode2.addChildNode(childNode21);
        TreeNode childNode3 = new TreeNode(3);
        treeNode.addChildNode(childNode1);
        treeNode.addChildNode(childNode2);
        treeNode.addChildNode(childNode3);

        dfs(treeNode);
    }
}
