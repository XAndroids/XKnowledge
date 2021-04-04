package com.java.xknowledge.leetcode.list.hascycle;

import java.util.HashSet;
import java.util.Set;

/**
 * 141. 环形链表
 * 链接：https://leetcode-cn.com/problems/linked-list-cycle/
 */
class Solution {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }

        Set<ListNode> nodes = new HashSet<>();//通过Set集合判断元素重复
        while (head != null) {
            if (!nodes.add(head)) {//相同相同节点
                return true;
            }
            head = head.next;
        }

        return false;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
//        listNode4.next = listNode2;

        System.out.println(hasCycle(listNode1));
    }
}
