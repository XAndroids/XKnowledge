package com.java.xknowledge.arithmetic.tree.haffman;

import java.util.List;

/**
 * 哈弗曼树
 */
public class HuffmanTree {
    /**
     * 构造哈弗曼树
     *
     * @param nodes 构造哈弗曼树的节点集合
     * @return 构造哈弗曼树的根节点
     */
    public static Node createTree(List<Node> nodes) {
        //如果节点列表中还有2个或者2个以上节点
        while (nodes.size() > 1) {
            //将当前所有页节点和子二叉树根节点进行排序
            sort(nodes);
            Node left = nodes.get(0);//取出最小的为左节点
            Node right = nodes.get(1);//取出第二小的为右节点
            Node parent = new Node(null, left.weight + right.weight);//构造新的二叉树节点，权重左右子树
            parent.leftChild = left;
            parent.rightChild = right;
            nodes.remove(0);//移除已经构造的最小和第二小节点
            nodes.remove(0);
            nodes.add(parent);//添加新的子二叉树根节点
        }

        return nodes.get(0);//节点1个或者构造树完成后，返回第0个根节点
    }

    /**
     * 冒泡排序，用于对节点进行排序，增序排序
     *
     * @param nodes 要进行排序的节点
     */
    private static void sort(List<Node> nodes) {
        if (nodes.size() <= 1) {
            return;
        }

        //找出最大元素的个数i
        for (int i = 0; i < nodes.size(); i++) {
            //每次找出第i个最大元素，都从第j个和j+1个进行比较
            for (int j = 0; j < nodes.size() - i - 1; j++) {
                //如果第j+1更小，则交换第j个大的，然后依次比较找出当前剩余最大的
                if (nodes.get(j + 1).weight < nodes.get(j).weight) {
                    Node temp = nodes.get(j + 1);
                    nodes.set(j + 1, nodes.get(j));
                    nodes.set(j, temp);
                }
            }
        }
    }

    /**
     * 递归打印哈弗曼树（先左子树，后右子树）
     *
     * @param root 要打印的哈弗曼树
     */
    static void printTree(Node root) {
        System.out.println(root.toString());
        if (root.leftChild != null) {
            printTree(root.leftChild);
        }
        if (root.rightChild != null) {
            printTree(root.rightChild);
        }
    }

    /**
     * 哈弗曼树的节点
     */
    static class Node<T> {
        private T data;//数据
        private int weight;//权重
        private Node leftChild;//左节点
        private Node rightChild;//右节点

        public Node(T data, int weight) {
            this.data = data;
            this.weight = weight;
        }

        public String toString() {
            return "Node[" + weight + ",data=" + data + "]";
        }
    }
}
