package com.java.xknowledge.leetcode.list.reverseList;

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
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }
}
