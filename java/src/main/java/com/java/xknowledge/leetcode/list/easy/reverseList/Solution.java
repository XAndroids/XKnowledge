package com.java.xknowledge.leetcode.list.easy.reverseList;

/**
 * 206、反转链表
 * https://leetcode-cn.com/problems/reverse-linked-list/
 */
public class Solution {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 思路：遍历链表时，需要pre、cur和next三个指针保留头、当前和下一个节点，否则无法操作链表反转
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode next = curr.next;//next节点保存下一个节点引用
            curr.next = prev;//倒转当前节点

            prev = curr;//前移pre，未下一个节点倒转做准备
            curr = next;//前移cur，为下一个节点倒转做准备
        }

        return prev;
    }
}
