package com.java.xknowledge.leetcode.list.Intersectionode;

/**
 * 160. 相交链表
 * 链接：https://leetcode-cn.com/problems/intersection-of-two-linked-lists
 */
class Solution {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        ListNode searchA = headA;//A/B链表开始遍历
        ListNode searchB = headB;

        while (searchA != searchB) {//香蕉节点是searchA和searchB同一个节点对象，不是数据相等
            searchA = searchA == null ? headB : searchA.next;//如果A或B走到头，切换到另外链表，消除长度差
            searchB = searchB == null ? headA : searchB.next;//否则继续向后
        }
        return searchA;
    }

    public static void main(String args) {

    }
}
