package com.java.xknowledge.leetcode.list.medium.sortlist;

/**
 * 148. 排序链表
 * 链接：https://leetcode-cn.com/problems/sort-list/
 */
class Solution {
    public class ListNode {
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
     * 单链表选择排序
     */
    public ListNode sortList(ListNode head) {
        if (head == null) {
            return null;
        }

        //当前循环选择最小值存放节点choiceNode，默认从head开始
        ListNode choiceNode = head;
        while (choiceNode != null) {
            //从choiceNode开始遍历，查找最小的节点
            ListNode minNode = choiceNode;
            ListNode traverseNode = choiceNode;
            while (traverseNode != null) {
                if (traverseNode.val < minNode.val) {
                    minNode = traverseNode;
                }
                traverseNode = traverseNode.next;
            }

            //将最小节点和choice进行交换
            int temp = minNode.val;
            minNode.val = choiceNode.val;
            choiceNode.val = temp;

            //继续从choice下一个节点开始查找剩下节点的最小值节点
            choiceNode = choiceNode.next;
        }

        return head;
    }
}
