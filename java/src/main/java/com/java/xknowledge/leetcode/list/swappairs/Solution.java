package com.java.xknowledge.leetcode.list.swappairs;

import java.util.List;

/**
 *
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * 示例 1：
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 * 示例 2：
 * <p>
 * 输入：head = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：head = [1]
 * 输出：[1]
 *  
 * <p>
 * 提示：
 * <p>
 * 链表中节点的数目在范围 [0, 100] 内
 * 0 <= Node.val <= 100
 * 链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs
 */
class Solution {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 交换两两相邻链表
     */
    public static ListNode swapPairs(ListNode head) {
        ListNode tempHeadNode = new ListNode();//创建头部临时节点辅助
        tempHeadNode.next = head;
        ListNode pre = tempHeadNode;//创建pre辅助指针

        while (pre.next != null && pre.next.next != null) {//判断后面节点还有一对交换
            ListNode node1 = pre.next;
            ListNode node2 = node1.next;//移动交换指针node1和node2

            pre.next = node2;//交换两个节点指针，注意从第一、三、二顺序
            node1.next = node2.next;
            node2.next = node1;

            pre = node1;//pre后移
        }

        return tempHeadNode.next;//返回链表头
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        listNode1.next = listNode2;
        listNode2.next = listNode3;

        ListNode result = swapPairs(listNode1);
        System.out.println(result.toString());
    }
}

