package com.java.xknowledge.leetcode.list.easy.Intersectionode;

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

        //A/B链表开始遍历
        ListNode searchA = headA;
        ListNode searchB = headB;

        //相交节点是searchA和searchB同一个节点对象，不是数据相等！！！！
        while (searchA != searchB) {
            searchA = searchA == null ? headB : searchA.next;//如果A或B走到头，切换到另外链表，消除长度差
            searchB = searchB == null ? headA : searchB.next;//否则继续向后
        }

        //找到相交节点，返回
        return searchA;
    }

    public static void main(String args) {

    }
}
