package com.java.xknowledge.leetcode.list.swappairs;

/**
 * 24、两两交换链表中的节点
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

