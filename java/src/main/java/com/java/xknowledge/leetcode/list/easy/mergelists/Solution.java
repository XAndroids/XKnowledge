package com.java.xknowledge.leetcode.list.easy.mergelists;

/**
 * 21. 合并两个有序链表
 * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
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

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();//创建辅助head节点
        ListNode pre = head;//创建辅助pre指针，用来串联合并链表

        while (l1 != null && l2 != null) {//当两个链表当前节点任意未null时，就不用合并操作
            if (l1.val < l2.val) {//根据两个链表当前节点大小，选择串联节点串联
                pre.next = l1;
                l1 = l1.next;
            } else {
                pre.next = l2;
                l2 = l2.next;
            }

            pre = pre.next;//pre辅助指针向后移动
        }

        if (l1 != null) pre.next = l1;//如果某个链表还存在，直接连接上即可
        if (l2 != null) pre.next = l2;

        return head.next;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(4);
        listNode1.next = listNode2;
        listNode2.next = listNode3;

        ListNode listNode4 = new ListNode(1);
        ListNode listNode5 = new ListNode(3);
        ListNode listNode6 = new ListNode(4);
        listNode4.next = listNode5;
        listNode5.next = listNode6;

        ListNode resultNode = mergeTwoLists(listNode1, listNode4);
        System.out.println(resultNode);
    }
}
