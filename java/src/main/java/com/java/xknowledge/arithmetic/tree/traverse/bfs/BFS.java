package com.java.xknowledge.arithmetic.tree.traverse.bfs;

import java.util.ArrayList;
import java.util.List;

/**
 * 树的广度遍历
 * 参考：https://www.cnblogs.com/liyao0312/p/11401019.html
 */
class BFS {
    static class TreeNode {
        int data;
        List<TreeNode> childNodeList;

        public TreeNode(int data) {
            this.data = data;
            this.childNodeList = new ArrayList<>();
        }

        public void addChildNode(TreeNode treeNode) {
            if (treeNode == null) {
                return;
            }

            childNodeList.add(treeNode);
        }
    }

    /**
     * 递归实现
     */
    public static void bfs(TreeNode rootNode) {
        if (rootNode == null) {
            return;
        }

        System.out.println(rootNode.data);//输出当前节点值

        if (rootNode.childNodeList != null) {
            bfsChild(rootNode.childNodeList);//递归遍历当前节点的孩子节点
        }
    }

    public static void bfsChild(List<TreeNode> childNodeList) {
        if (childNodeList == null || childNodeList.size() == 0) {
            return;
        }

        List<TreeNode> allChildNodeList = new ArrayList<>();
        for (int i = 0; i < childNodeList.size(); i++) {
            System.out.println(childNodeList.get(i).data);//输出所有孩子节点
            allChildNodeList.addAll(childNodeList.get(i).childNodeList);//收集下一层孩子节点
        }

        bfsChild(allChildNodeList);//递归输出下一层孩子节点
    }

    /**
     * 非递归实现
     */
    public void bfs2(TreeNode rootNode) {

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

        bfs(treeNode);
    }
}
