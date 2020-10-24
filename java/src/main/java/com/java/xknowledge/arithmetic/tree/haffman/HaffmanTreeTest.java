package com.java.xknowledge.arithmetic.tree.haffman;

import java.util.ArrayList;
import java.util.List;

/**
 * 哈弗曼树测试
 * 参考：《享学1：008 树(上)：树和二叉树的基本概念，以及huffman编码的手写实现》
 */
class HaffmanTreeTest {
    public static void main(String[] args) {
        List<HuffmanTree.Node> nodes = new ArrayList<HuffmanTree.Node>();
        //把节点加入至list中
        nodes.add(new HuffmanTree.Node("a", 10));
        nodes.add(new HuffmanTree.Node("b", 15));
        nodes.add(new HuffmanTree.Node("c", 12));
        nodes.add(new HuffmanTree.Node("d", 3));
        nodes.add(new HuffmanTree.Node("e", 4));
        nodes.add(new HuffmanTree.Node("f", 13));
        nodes.add(new HuffmanTree.Node("g", 1));
        //进行哈夫曼树的构造
        HuffmanTree.Node root = HuffmanTree.createTree(nodes);
        //打印哈夫曼树
        HuffmanTree.printTree(root);
    }
}
