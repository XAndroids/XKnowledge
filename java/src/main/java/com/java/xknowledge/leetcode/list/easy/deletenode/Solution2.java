package com.java.xknowledge.leetcode.list.easy.deletenode;

/**
 * 面试题 02.03. 删除中间节点
 * 链接：https://leetcode-cn.com/problems/delete-middle-node-lcci/
 */
class Solution2 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public void deleteNode(ListNode node) {
        //复制下一个节点的值
        node.val = node.next.val;
        //直接删除下一个节点，从而删除想要删除节点
        node.next = node.next.next;
    }
}
